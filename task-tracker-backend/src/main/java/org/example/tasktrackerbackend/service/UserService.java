package org.example.tasktrackerbackend.service;

import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.entity.User;

public interface UserService {

    User saveUser(User user);
}
