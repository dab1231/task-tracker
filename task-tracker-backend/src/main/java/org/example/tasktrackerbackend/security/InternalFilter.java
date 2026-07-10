package org.example.tasktrackerbackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class InternalFilter extends OncePerRequestFilter {

    @Value("${internal.api.key}")
    private String internalKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        var keyHeader = request.getHeader("X-Internal-Api-Key");
        if (Objects.equals(keyHeader, internalKey)) {

            var authorityList = AuthorityUtils.createAuthorityList("INTERNAL_SERVICE");
            var authenticationToken = new UsernamePasswordAuthenticationToken("internal-service", "internal-service", authorityList);
            var emptyContext = SecurityContextHolder.createEmptyContext();

            emptyContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(emptyContext);
        }
        filterChain.doFilter(request, response);
    }
}
