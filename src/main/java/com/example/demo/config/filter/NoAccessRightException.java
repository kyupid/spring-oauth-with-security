package com.example.demo.config.filter;

import org.springframework.security.core.AuthenticationException;

public class NoAccessRightException extends AuthenticationException {
    public NoAccessRightException() {
        super("몰랑");
    }
}
