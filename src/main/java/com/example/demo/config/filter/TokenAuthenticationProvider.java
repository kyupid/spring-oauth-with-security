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

        CustomAuthToken authenticatedToken = (CustomAuthToken) authentication;
        String authNeeded = String.valueOf(authenticatedToken.getPrincipal());
        // 이 principal이 유효한 토큰인지 검증하는게 오겠지
        // 예를 들어 아이디,비번 로그인 유저라면 실제 인증하는 로직을 아래에 구현하고
        // 각종 처리를 구현
        // 비번이 일치하는지
        // 아이디로 회원을 조회 했을 때 존재하는 회원인지
        // 기타 등등과 적절한 예외 처리
        // 해당 객체를 true 라고 하면 인증이 된다.
        authenticatedToken.setAuthenticated(true);

        return authenticatedToken;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        // 인증객체에서 선언한 객체는 reflection 을 통해서 해당 객체인지 파악하는데 이용된다.
        return CustomAuthToken.class.isAssignableFrom(authentication);
    }
}
