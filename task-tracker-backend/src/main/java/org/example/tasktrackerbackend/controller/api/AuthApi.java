package org.example.tasktrackerbackend.controller.api;

import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.dto.response.JwtAuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthApi {

    @PostMapping("/login")
    ResponseEntity<JwtAuthResponse> signIn(@RequestBody UserRequest userRequest);
}
