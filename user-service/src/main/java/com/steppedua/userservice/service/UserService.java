package com.steppedua.userservice.service;

import com.steppedua.userservice.dto.UserCreateRequestDto;
import com.steppedua.userservice.tables.pojos.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID userId);

    UUID createUser(UserCreateRequestDto user);
}
