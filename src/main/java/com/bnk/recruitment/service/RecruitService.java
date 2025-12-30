package com.bnk.recruitment.service;

import com.bnk.recruitment.repository.CrawlLogRepository;
import com.bnk.recruitment.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final CrawlLogRepository crawlLogRepository;

    public java.util.List<com.bnk.recruitment.dto.RecruitPost> findAll() {
        return recruitRepository.findAll();
    }

    public java.util.List<com.bnk.recruitment.dto.RecruitPost> findActivePosts() {
        return recruitRepository.findByStatus("접수중");
    }

    public java.util.List<com.bnk.recruitment.dto.CrawlLog> findRecentCrawlLogs() {
        return crawlLogRepository.findTop10ByOrderByCrawlTimeDesc();
    }
}
