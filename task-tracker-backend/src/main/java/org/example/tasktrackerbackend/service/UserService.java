package org.example.tasktrackerbackend.service;

import org.example.tasktrackerbackend.dto.response.UserResponse;
import org.example.tasktrackerbackend.entity.User;

public interface UserService {

    User saveUser(User user);

    UserResponse findByEmail(String email);

    User findUserByEmail(String email);
}
