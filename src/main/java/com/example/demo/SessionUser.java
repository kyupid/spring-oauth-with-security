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


    // 추가정보까지 제출받은것은 회원가입이 완료됐으므로 접근가능하도록 ROLE을 USER로 변경한다
    public void setRole(Role role) {
        this.role = role;
    }
}
