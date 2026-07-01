package org.example.tasktrackerbackend.service;

import org.example.tasktrackerbackend.dto.request.UserRequest;

public interface UserService {

    void saveUser(UserRequest userRequest);
}
