package com.bnk.recruitment.scheduler;

import com.bnk.recruitment.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CrawlingScheduler {

    private final CrawlerService crawlerService;

    @Scheduled(cron = "0 */5 * * * *")
    public void runCrawler() {
        log.info("Scheduled task triggered.");
        crawlerService.crawlBnksys();
    }
}
