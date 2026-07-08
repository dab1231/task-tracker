package org.example.tasktrackeremailsender.kafka.service;

import org.example.tasktrackeremailsender.kafka.dto.EmailMessage;

public interface EmailService {

    void sendEmail(EmailMessage emailMessage);
}
