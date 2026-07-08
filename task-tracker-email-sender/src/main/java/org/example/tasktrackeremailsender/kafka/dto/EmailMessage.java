package org.example.tasktrackeremailsender.kafka.dto;

public record EmailMessage(String recipientEmail, String title, String description) {
}
