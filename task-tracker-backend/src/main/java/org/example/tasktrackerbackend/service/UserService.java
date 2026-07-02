package org.example.tasktrackerbackend.service;

import org.example.tasktrackerbackend.entity.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findByEmail(String email);
}
