package org.example.tasktrackerbackend.service;

import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.dto.response.JwtAuthResponse;

public interface AuthService {

    JwtAuthResponse singUp(UserRequest userRequest);

    JwtAuthResponse singIn(UserRequest userRequest);

}
