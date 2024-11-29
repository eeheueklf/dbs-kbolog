package com.example.kbolog.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void validateUsername(String username) {
        if (username.length() < 5 || username.length() > 20) {
            throw new IllegalArgumentException("아이디는 5자 이상, 20자 이하로 입력해야 합니다.");
        }
    }

    public void validatePassword(String password) {
        if (password.length() < 8 || password.length() > 30) {
            throw new IllegalArgumentException("비밀번호는 8자 이상, 30자 이하로 입력해야 합니다.");
        }
    }
}
