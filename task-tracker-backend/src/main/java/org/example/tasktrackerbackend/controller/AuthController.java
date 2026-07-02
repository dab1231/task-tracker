package org.example.tasktrackerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.controller.api.AuthApi;
import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.dto.response.JwtAuthResponse;
import org.example.tasktrackerbackend.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<JwtAuthResponse> signIn(UserRequest userRequest) {

        var jwtAuthResponse = authService.singIn(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtAuthResponse.token());
        return ResponseEntity
                .status(200)
                .headers(headers)
                .build();
    }
}
