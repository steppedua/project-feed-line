package com.steppedua.userservice.service;

import com.steppedua.userservice.repository.UserDAO;
import com.steppedua.userservice.dto.UserCreateRequestDto;
import com.steppedua.userservice.tables.pojos.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@SpringBootTest
//@Testcontainers
class UserServiceTest {
//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3-alpine")
//            .withDatabaseName("test")
//            .withUsername("postgres")
//            .withPassword("postgres")
//            .withExposedPorts(5432);

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;
    private String uuid;
    private User user;

    @BeforeEach
    public void init() {
        uuid = "66e644eb-e4c3-4c2d-9d0d-ca0a19437fd4";
        user = new User(UUID.fromString(uuid),
                "ACTIVE", "Ivan", "Ivanov", OffsetDateTime.now(), null);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        final var userCreateRequestDto = new UserCreateRequestDto("Ivan", "Ivanov");

        given(userDAO.createUser(userCreateRequestDto)).willReturn(UUID.fromString(uuid));

        final var userUUID = userService.createUser(userCreateRequestDto);
        Assertions.assertThat(userUUID).isEqualTo(UUID.fromString(uuid));
    }

    @Test
    void shouldGetUserByIdTest() {
        given(userDAO.getUserById(UUID.fromString(uuid))).willReturn(Optional.of(user));

        final var userById = userService.getUserById(UUID.fromString(uuid));

        Assertions.assertThat(userById).isNotNull();
    }
}