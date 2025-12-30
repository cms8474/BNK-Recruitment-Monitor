package com.bnk.recruitment.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "RECRUIT_POST")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitPost {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TITLE", length = 500)
    private String title;

    @Column(name = "URL", length = 1000)
    private String url;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "LAST_UPDATED")
    private Timestamp lastUpdated;

    @Column(name = "STATUS", length = 20)
    private String status;

}
