package org.example.tasktrackeremailsender.kafka.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackeremailsender.kafka.dto.EmailMessage;
import org.example.tasktrackeremailsender.kafka.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendEmail(EmailMessage emailMessage) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(emailMessage.recipientEmail());
        message.setSubject(emailMessage.title());
        message.setText(emailMessage.description());
        mailSender.send(message);
    }
}
