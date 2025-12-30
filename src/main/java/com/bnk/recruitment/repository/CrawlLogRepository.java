package com.bnk.recruitment.repository;

import com.bnk.recruitment.dto.CrawlLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrawlLogRepository extends JpaRepository<CrawlLog, Long> {
    List<CrawlLog> findTop10ByOrderByCrawlTimeDesc();
}
