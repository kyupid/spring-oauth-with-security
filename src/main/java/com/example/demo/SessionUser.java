package com.example.demo;

import com.example.demo.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private Role role;

    // 추가 정보 시작
    private String location;
    private Integer age;
    // 추가 정보 끝

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.role = user.getRole();
    }

    // 추가정보 페이지에서 받아서 사용 시작
    public void setLocation(String location) {
        this.location = location;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    // 추가정보 페이지에서 받아서 사용 끝
}
