package org.example.tasktrackerbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskCreateRequest(
        @Size(min = 0, max = 255)
        @NotNull
        String title,
        String description) {
}
