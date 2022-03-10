package com.example.demo.config.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CustomAuthToken extends AbstractAuthenticationToken {

    private final String principal;
    private Object credentials;

    public CustomAuthToken(String principal) {
        super(null);
        this.principal = principal;
        this.credentials = null;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
