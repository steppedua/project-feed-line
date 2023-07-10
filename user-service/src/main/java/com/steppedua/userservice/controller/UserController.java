package com.steppedua.userservice.controller;

import com.steppedua.userservice.dto.UserCreateRequestDto;
import com.steppedua.userservice.service.UserService;
import com.steppedua.userservice.tables.pojos.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UUID createUser(UserCreateRequestDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }
}


