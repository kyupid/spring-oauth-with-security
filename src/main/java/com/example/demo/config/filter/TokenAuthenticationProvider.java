package com.example.demo.config.filter;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        Authentication authenticatedToken = authentication;
        // 해당 객체를 true 라고 하면 인증이 된다.
        // 여기서 로직을 태워서 만약 인증이 아니라면 throw Authentication 을 던져주면 된다.
        authenticatedToken.setAuthenticated(true);

        return authenticatedToken;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        // 인증객체에서 선언한 객체는 reflection 을 통해서 해당 객체인지 파악하는데 이용된다.
        return CustomAuthToken.class.isAssignableFrom(authentication);
    }
}
