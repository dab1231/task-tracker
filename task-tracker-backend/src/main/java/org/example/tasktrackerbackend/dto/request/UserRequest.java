package org.example.tasktrackerbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @Email(message = "This field must contain valid email")
        @NotBlank(message = "Email can not be blank")
        String email,
        @Size(min = 5, max = 20, message = "Password must be between 5 and 20")
        @NotBlank
        String password) {
}
