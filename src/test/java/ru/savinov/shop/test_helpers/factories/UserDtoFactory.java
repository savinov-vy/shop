package ru.savinov.shop.test_helpers.factories;

import ru.savinov.shop.controllers.dto.UserDto;

public class UserDtoFactory {

    public static UserDto of() {
        return UserDto.of();
    }

    public static UserDto ofReg() {
        return UserDto.builder()
                .login("testLogin")
                .password("testPassword")
                .confirmPassword("testPassword")
                .build();
    }
}
