package com.steppedua.userservice.service;

import com.steppedua.userservice.repository.UserDAO;
import com.steppedua.userservice.dto.UserCreateRequestDto;
import com.steppedua.userservice.tables.pojos.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public User getUserById(UUID userId) {
        return userDAO.getUserById(userId)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public UUID createUser(UserCreateRequestDto user) {
        return userDAO.createUser(user);
    }
}
