package org.example.tasktrackerbackend.controller.api;

import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserApi {

    @PostMapping
    ResponseEntity<Void> registerUser(@Validated @RequestBody UserRequest userRequest);

    @GetMapping
    ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal String email);
}
