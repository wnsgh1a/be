package com.test.test_backend.service;

import com.test.test_backend.entity.User;
import com.test.test_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long signup(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("이미 존재하는 이메일입니다.");
                });
        return userRepository.save(user).getId();
    }
}