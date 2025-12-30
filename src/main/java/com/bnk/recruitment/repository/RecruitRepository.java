package com.bnk.recruitment.repository;

import com.bnk.recruitment.dto.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<RecruitPost, String> {
    boolean existsByUrl(String url);

    List<RecruitPost> findByStatus(String status);
}
