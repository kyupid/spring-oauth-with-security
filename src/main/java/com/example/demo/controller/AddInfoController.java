package com.example.demo.controller;

import com.example.demo.service.AddInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AddInfoController {

    private final AddInfoService addInfoService;
    private final HttpSession httpSession;

    @GetMapping("/addInfo")
    public String index() {
        return "/addInfo";
    }

    @PostMapping("/addInfo/signup")
    public String signup(HttpServletRequest request) {
        addInfoService.saveSessionUser(request, httpSession);
        return "redirect:/";
    }
}
