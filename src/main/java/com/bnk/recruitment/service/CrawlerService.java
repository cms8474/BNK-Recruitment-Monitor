package com.bnk.recruitment.service;

import com.bnk.recruitment.dto.CrawlLog;
import com.bnk.recruitment.dto.RecruitPost;
import com.bnk.recruitment.repository.CrawlLogRepository;
import com.bnk.recruitment.repository.RecruitRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerService {

    private final RecruitRepository recruitRepository;
    private final MailService mailService;
    private final CrawlLogRepository crawlLogRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void crawlBnksys() {
        log.info("Starting crawler job...");
        try {
            // 1. Fetch JSON data from API
            String jsonResponse = Jsoup.connect("https://bnksys.recruiter.co.kr/app/jobnotice/list.json")
                    .data("appsiteSn", "10869")
                    .data("pageSize", "50")
                    .ignoreContentType(true)
                    .method(Connection.Method.POST)
                    .execute()
                    .body();

            // 2. Parse JSON
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode listNode = rootNode.path("list");

            List<RecruitPost> currentPosts = new ArrayList<>();
            List<String> activeTitles = new ArrayList<>();

            if (listNode.isArray()) {
                for (JsonNode node : listNode) {
                    // Extract data
                    String id = node.path("jobnoticeSn").asText();
                    String title = node.path("jobnoticeName").asText();
                    String receiptState = node.path("receiptState").asText();
                    long startDateMillis = node.path("applyStartDate").path("time").asLong();
                    long endDateMillis = node.path("applyEndDate").path("time").asLong();

                    String url = "https://bnksys.recruiter.co.kr/app/jobnotice/view?systemKindCode=MRS2&jobnoticeSn="
                            + id;

                    // Only process "접수중" posts or interesting ones
                    if ("접수중".equals(receiptState)) {
                        activeTitles.add(title);

                        RecruitPost post = RecruitPost.builder()
                                .id(id)
                                .title(title)
                                .url(url)
                                .status(receiptState)
                                .startDate(new Date(startDateMillis))
                                .endDate(new Date(endDateMillis))
                                .lastUpdated(new Timestamp(System.currentTimeMillis()))
                                .build();

                        currentPosts.add(post);

                        // 3. Check for new posts
                        if (!recruitRepository.existsById(id)) {
                            log.info("New recruit post found: {}", title);
                            recruitRepository.save(post);
                            mailService.sendNotificationEmail(
                                    "[BNK 채용 알림] 새로운 공고가 등록되었습니다: " + title,
                                    "새로운 공고가 등록되었습니다.\n제목: " + title + "\n상태: " + receiptState + "\n링크: " + url);
                        } else {
                            // Update existing post (e.g. status change or date update)
                            // We can check if status changed, but for now just save to update lastUpdated
                            RecruitPost existing = recruitRepository.findById(id).orElse(post);
                            if (!existing.getStatus().equals(receiptState)) {
                                existing.setStatus(receiptState);
                                recruitRepository.save(existing);
                            }
                        }
                    }
                }
            }

            // 4. Check for closed posts
            // Find all posts in DB that are currently "접수중"
            List<RecruitPost> dbActivePosts = recruitRepository.findByStatus("접수중");
            int closedCount = 0;

            // Set of IDs found in current crawl
            Set<String> currentIds = currentPosts.stream()
                    .map(RecruitPost::getId)
                    .collect(Collectors.toSet());

            for (RecruitPost dbPost : dbActivePosts) {
                if (!currentIds.contains(dbPost.getId())) {
                    log.info("Recruit post closed: {}", dbPost.getTitle());
                    dbPost.setStatus("종료");
                    dbPost.setLastUpdated(new Timestamp(System.currentTimeMillis()));
                    recruitRepository.save(dbPost);
                    closedCount++;
                }
            }

            // 5. Log result
            CrawlLog crawlLog = CrawlLog.builder()
                    .crawlTime(LocalDateTime.now())
                    .activeCount(currentPosts.size())
                    .activeTitles(truncate(String.join(",", activeTitles), 4000))
                    .build();

            crawlLogRepository.saveAndFlush(crawlLog);
            log.info("Crawl finished. Active: {}, Closed: {}", currentPosts.size(), closedCount);

        } catch (Exception e) {
            log.error("Error during crawling", e);
        }
    }

    private String truncate(String value, int length) {
        if (value != null && value.length() > length) {
            return value.substring(0, length);
        }
        return value;
    }
}
