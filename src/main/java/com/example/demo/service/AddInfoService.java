package com.example.demo.service;

import com.example.demo.SessionUser;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

        User user = new User(sessionUser);
        userRepository.save(user);
    }
}
