package org.example.tasktrackerbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasktrackerbackend.dto.request.UserRequest;
import org.example.tasktrackerbackend.dto.response.JwtAuthResponse;
import org.example.tasktrackerbackend.entity.User;
import org.example.tasktrackerbackend.enums.Role;
import org.example.tasktrackerbackend.kafka.service.EmailService;
import org.example.tasktrackerbackend.security.CustomUserDetails;
import org.example.tasktrackerbackend.security.jwt.JwtService;
import org.example.tasktrackerbackend.service.AuthService;
import org.example.tasktrackerbackend.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public JwtAuthResponse singUp(UserRequest userRequest) {
        var user = User.builder()
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .role(Role.USER)
                .build();

        var savedUser = userService.saveUser(user);
        var token = jwtService.generateToken(new CustomUserDetails(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getRole()
        ));
        emailService.sendWelcomeEmail(savedUser.getEmail());
        return new JwtAuthResponse(token);
    }

    @Override
    public JwtAuthResponse singIn(UserRequest userRequest) {
        var user = userService.findUserByEmail(userRequest.email());

        if (passwordEncoder.matches(userRequest.password(), user.getPassword())) {
            var token = jwtService.generateToken(new CustomUserDetails(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
            ));
            return new JwtAuthResponse(token);
        } else {
            throw new BadCredentialsException("Password in not correct");
        }
    }
}
