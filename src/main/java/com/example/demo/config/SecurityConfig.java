package com.example.demo.config;

import com.example.demo.config.filter.CustomAuthToken;
import com.example.demo.config.filter.HeaderTestKeyCheckFilter;
import com.example.demo.config.filter.TokenAuthenticationProvider;
import com.example.demo.service.CustomOAuth2UserService;
import com.example.demo.Role;
import jdk.nashorn.internal.parser.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationProvider tokenAuthenticationProvider;
    private final HeaderTestKeyCheckFilter headerTestKeyCheckFilter;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.
                        USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

        // BasicAuthenticationFilter 앞에서 필터링하겠다.
        http.addFilterBefore(headerTestKeyCheckFilter, BasicAuthenticationFilter.class);

        // Authentication 을 authenticate한다....?
        http.authenticationProvider(tokenAuthenticationProvider);
    }
}
