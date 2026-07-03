package org.example.tasktrackerbackend.dto.response;

import org.example.tasktrackerbackend.enums.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String title, String description, TaskStatus status, LocalDateTime completedAt) {
}
