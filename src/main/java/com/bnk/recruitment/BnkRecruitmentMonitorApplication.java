package com.bnk.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BnkRecruitmentMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BnkRecruitmentMonitorApplication.class, args);
    }

}
