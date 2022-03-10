package com.example.demo.config.filter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class HeaderTestKeyCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String value = getValueFromTestKey(request).orElseThrow(NoAccessRightException::new);
        CustomAuthToken authenticatedToken = new CustomAuthToken(value);
        SecurityContextHolder.getContext().setAuthentication(authenticatedToken);

        filterChain.doFilter(request, response);
    }

    private Optional<String> getValueFromTestKey(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("testKey"));
    }
}
