package org.example.tasktrackerbackend.kafka.dto;

public record EmailMessage(String recipientEmail, String title, String description) {
}
