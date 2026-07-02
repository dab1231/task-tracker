package org.example.tasktrackerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.controller.api.UserApi;
import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<Void> registerUser(UserRequest userRequest) {

        var jwtAuthResponse = authService.singUp(userRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + jwtAuthResponse.token());
        return ResponseEntity
                .status(200)
                .headers(httpHeaders)
                .build();
    }
}
