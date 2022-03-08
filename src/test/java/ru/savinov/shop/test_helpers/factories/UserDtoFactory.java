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

    public static UserDto emptyLogin() {
        return UserDto.builder()
                .login("")
                .password("testPassword")
                .confirmPassword("testPassword")
                .build();
    }

    public static UserDto notConfirmPassword() {
        return UserDto.builder()
                .login("testLogin")
                .password("testPassword")
                .confirmPassword("notConfirmPassword")
                .build();
    }

    public static UserDto shortLogin() {
        return UserDto.builder()
                .login("te")
                .password("testPassword")
                .confirmPassword("testPassword")
                .build();
    }

    public static UserDto emptyPassword() {
        return UserDto.builder()
                .login("testLogin")
                .password("")
                .confirmPassword("testPassword")
                .build();
    }

}
