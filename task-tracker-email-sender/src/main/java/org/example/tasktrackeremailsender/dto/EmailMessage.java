package org.example.tasktrackeremailsender.dto;

public record EmailMessage(String recipientEmail, String title, String description) {
}
