package org.example.tasktrackerbackend.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserDetails userDetails);

    String extractEmail(String token);

    boolean verifyToken(String token);
}
