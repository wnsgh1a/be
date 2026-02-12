package com.test.test_backend.service;

import com.test.test_backend.entity.User;
import com.test.test_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입 로직
    @Transactional
    public Long signup(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("이미 존재하는 이메일입니다.");
                });
        return userRepository.save(user).getId();
    }

    // 로그인 로직 (추가됨)
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("이메일 또는 비밀번호가 일치하지 않습니다."));
    }
}