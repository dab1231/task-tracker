package org.example.tasktrackerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.controller.api.UserApi;
import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.dto.response.UserResponse;
import org.example.tasktrackerbackend.service.AuthService;
import org.example.tasktrackerbackend.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final AuthService authService;
    private final UserService userService;

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

    @Override
    public ResponseEntity<UserResponse> getCurrentUser(String email) {

        var userResponse = userService.findByEmail(email);

        return ResponseEntity.status(200).body(userResponse);
    }
}
