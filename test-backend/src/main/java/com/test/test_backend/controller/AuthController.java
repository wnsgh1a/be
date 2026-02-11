package com.test.test_backend.controller;

import com.test.test_backend.entity.User;
import com.test.test_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        try {
            userService.signup(user);
            return "회원가입 성공!";
        } catch (Exception e) {
            return "회원가입 실패: " + e.getMessage();
        }
    }
}