package org.example.tasktrackerbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tasktrackerbackend.dto.response.UserResponse;
import org.example.tasktrackerbackend.entity.User;
import org.example.tasktrackerbackend.exception.UserAlreadyExistsException;
import org.example.tasktrackerbackend.repository.UserRepository;
import org.example.tasktrackerbackend.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public UserResponse findByEmail(String email) {
        var maybeUser = userRepository.findByEmail(email);
        if (maybeUser.isPresent()) {
            var user = maybeUser.get();
            return new UserResponse(user.getId(), user.getEmail());
        } else {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
    }

    @Override
    public User findUserByEmail(String email) {
        var maybeUser = userRepository.findByEmail(email);
        if (maybeUser.isPresent()) {
            return maybeUser.get();
        } else {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
    }

    @Override
    public User findById(Long id) {
        var maybeUser = userRepository.findById(id);
        if (maybeUser.isPresent()) {
            return maybeUser.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public Long getIdByEmail(String email) {
        var maybeUser = userRepository.findByEmail(email);
        if (maybeUser.isPresent()) {
            return maybeUser.get().getId();
        } else {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
    }


}
