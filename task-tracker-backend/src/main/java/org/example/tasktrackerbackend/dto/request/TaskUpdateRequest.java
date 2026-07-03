package org.example.tasktrackerbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.tasktrackerbackend.enums.TaskStatus;

public record TaskUpdateRequest(
        @Size(min = 0, max = 255)
        @NotNull
        String title,
        String description,
        @NotNull
        TaskStatus status) {
}
