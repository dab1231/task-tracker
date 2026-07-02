package org.example.tasktrackerbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.entity.User;
import org.example.tasktrackerbackend.enums.Role;
import org.example.tasktrackerbackend.exception.UserAlreadyExistsException;
import org.example.tasktrackerbackend.repository.UserRepository;
import org.example.tasktrackerbackend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        userRepository.findByEmail(user.getEmail())
                .ifPresent(maybeUser -> {
                    throw new UserAlreadyExistsException("User with email:" + maybeUser.getEmail() + " already exists");
                });

        var savedUser = userRepository.save(user);
        log.info("User with email: {} was registered", user.getEmail());
        return savedUser;
    }
}
