package org.example.tasktrackerbackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("Role", userDetails.getAuthorities())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(getSecretKey(secret))
                .compact();
    }

    @Override
    public String extractEmail(String token) {
        var parser = getJwtParser();
        var claimsJws = parser.parseSignedClaims(token);

        return claimsJws
                .getPayload()
                .getSubject();
    }

    private JwtParser getJwtParser() {
        var parser = Jwts.parser()
                .verifyWith(getSecretKey(secret))
                .build();
        return parser;
    }

    @Override
    public boolean verifyToken(String token) {
        var parser = getJwtParser();
        try {
            var claimsJws = parser.parseSignedClaims(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private SecretKey getSecretKey(String key) {
        return Keys.hmacShaKeyFor(key.getBytes());
    }
}
