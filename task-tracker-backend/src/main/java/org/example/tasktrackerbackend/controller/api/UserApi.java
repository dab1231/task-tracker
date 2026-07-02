package org.example.tasktrackerbackend.controller.api;

import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserApi {

    @PostMapping
    ResponseEntity<Void> registerUser(@RequestBody UserRequest userRequest);
}
