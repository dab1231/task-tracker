package org.example.tasktrackerbackend.kafka.service;

public interface EmailService {

    void sendWelcomeEmail(String recipientEmail);
}
