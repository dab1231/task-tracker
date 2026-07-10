package org.example.tasktrackerscheduler.kafka.dto;

public record EmailMessage(String recipientEmail, String title, String description) {
}
