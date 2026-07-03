package org.example.tasktrackerbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.tasktrackerbackend.enums.TaskStatus;

public record TaskUpdateRequest(
        @Size(max = 255)
        @NotBlank
        String title,
        String description,
        @NotNull
        TaskStatus status) {
}
