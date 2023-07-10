package com.steppedua.userservice.service;

import com.steppedua.userservice.UserRepository;
import com.steppedua.userservice.dto.UserCreateRequestDto;
import com.steppedua.userservice.tables.pojos.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User getUserById(UUID userId) {
        return userRepository.getUserById(userId)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public UUID createUser(UserCreateRequestDto user) {
        return userRepository.createUser(user);
    }
}
