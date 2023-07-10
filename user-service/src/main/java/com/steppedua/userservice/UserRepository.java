package com.steppedua.userservice;


import com.steppedua.userservice.dto.UserCreateRequestDto;
import com.steppedua.userservice.tables.pojos.User;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.steppedua.userservice.tables.User.USER;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final DSLContext dsl;

    public UUID createUser(UserCreateRequestDto user) {
        final var userRecord = dsl.insertInto(USER)
                .set(USER.ID, UUID.randomUUID())
                .set(USER.FIRST_NAME, user.getFirstName())
                .set(USER.LAST_NAME, user.getLastName())
                .set(USER.CREATED_AT, OffsetDateTime.now())
                .returning(USER.ID)
                .fetchOne();

        if (userRecord == null) {
            throw new RuntimeException();
        }

        return userRecord.getId();
    }

    public Optional<User> getUserById(UUID userId) {
        final var userRecord = dsl
                .select(USER.ID, USER.STATUS, USER.FIRST_NAME, USER.LAST_NAME,
                        USER.CREATED_AT, USER.UPDATED_AT)
                .from(USER)
                .where(USER.ID.eq(userId))
                .fetchOne();


        if (userRecord == null) {
            throw new RuntimeException();
        }

        return Optional.ofNullable(userRecord.into(User.class));
    }
}