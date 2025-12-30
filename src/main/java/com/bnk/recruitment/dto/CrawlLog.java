package com.bnk.recruitment.dto;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CRAWL_LOG")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrawlLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRAWL_LOG_SEQ_GEN")
    @SequenceGenerator(name = "CRAWL_LOG_SEQ_GEN", sequenceName = "SEQ_CRAWL_LOG_ID", allocationSize = 1)
    @Column(name = "LOG_ID", nullable = false)
    private Long logId;

    @Column(name = "CRAWL_TIME")
    private LocalDateTime crawlTime;

    @Column(name = "ACTIVE_COUNT")
    private Integer activeCount;

    @Column(name = "ACTIVE_TITLES", length = 4000)
    private String activeTitles;
}