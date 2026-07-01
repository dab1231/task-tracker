package org.example.tasktrackerbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.entity.User;
import org.example.tasktrackerbackend.enums.Role;
import org.example.tasktrackerbackend.exception.UserAlreadyExistsException;
import org.example.tasktrackerbackend.repository.UserRepository;
import org.example.tasktrackerbackend.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(UserRequest userRequest) {

        userRepository.findByEmail(userRequest.email())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("User with email:" + userRequest.email() + " already exists");
                });

        var user = User.builder()
                .email(userRequest.email())
                .password(userRequest.password())
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }
}
