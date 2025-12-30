package com.bnk.recruitment.controller;

import com.bnk.recruitment.service.CrawlerService;
import com.bnk.recruitment.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;
    private final CrawlerService crawlerService;

    @GetMapping
    public String list(Model model) {
        log.info("Requesting Recruit List Page");
        model.addAttribute("activePosts", recruitService.findActivePosts());
        model.addAttribute("crawlLogs", recruitService.findRecentCrawlLogs());
        return "recruit/list";
    }

    // 수동 크롤링 버튼
    @PostMapping("/crawl")
    public String runCrawlManual() {
        log.info("Manual crawling requested via button.");
        crawlerService.crawlBnksys();
        return "redirect:/recruit"; // 작업 후 리스트 페이지 새로고침
    }
}
