package org.example.tasktrackerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.controller.api.UserApi;
import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<Void> registerUser(UserRequest userRequest) {

        userService.saveUser(userRequest);
        return ResponseEntity.status(200).build();
    }
}
