package org.example.tasktrackerbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskCreateRequest(
        @Size(max = 255)
        @NotBlank
        String title,
        String description) {
}
