package com.bnk.recruitment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendNotificationEmail(String subject, String content) {
        log.info("Sending notification email for new recruitment post.");
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("zxy0575@naver.com");
            message.setSubject(subject);
            message.setText(content);
            javaMailSender.send(message);
            log.info("Email sent successfully.");
        } catch (Exception e) {
            log.error("Failed to send email", e);
        }
    }
}
