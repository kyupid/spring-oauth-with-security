package com.example.demo.service;

import com.example.demo.Role;
import com.example.demo.SessionUser;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddInfoService {

    private final UserRepository userRepository;

    public void saveSessionUser(HttpServletRequest request, HttpSession httpSession) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        String location = request.getParameter("location");
        Integer age = Integer.parseInt(request.getParameter("age"));
        sessionUser.setLocation(location);
        sessionUser.setAge(age);

        // 추가정보까지 제출받은것은 회원가입이 완료됐으므로 접근가능하도록 ROLE을 USER로 변경한다
        sessionUser.setRole(Role.USER);
        httpSession.setAttribute("user", sessionUser);
        User user = new User(sessionUser);
        System.out.println(httpSession.getAttribute("SPRING_SECURITY_CONTEXT"));

        // 세션에 있는 SPRING_SECURITY_CONTEXT 의 ROLE 을 변경해준다.
        updateRoleSpringSecurityContextSession(httpSession);

        userRepository.save(user);
    }

    private void updateRoleSpringSecurityContextSession(HttpSession httpSession) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        List<GrantedAuthority> actualAuthorities = roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getKey())).collect(Collectors.toList());
        Authentication newAuth = new
                UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), actualAuthorities);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(newAuth);
        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", context);
        // https://stackoverflow.com/questions/50585731/spring-security-how-to-change-user-roles-without-login-and-logout
        // https://okky.kr/article/256863
        System.out.println("변경후: " + httpSession.getAttribute("SPRING_SECURITY_CONTEXT"));
    }
}
