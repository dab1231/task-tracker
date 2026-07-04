package org.example.tasktrackerbackend.dto.response;

import lombok.Builder;
import org.example.tasktrackerbackend.enums.TaskStatus;

import java.time.LocalDateTime;

@Builder
public record TaskResponse(Long id, String title, String description, TaskStatus status, LocalDateTime completedAt) {
}
