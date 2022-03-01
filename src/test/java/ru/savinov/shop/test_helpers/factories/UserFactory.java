package ru.savinov.shop.test_helpers.factories;

import ru.savinov.shop.entities.User;

public class UserFactory {

    public static User of() {
        return User.of();
    }

    public static User ofReg() {
        return User.builder()
                .login("testLogin")
                .password("testPassword")
                .build();
    }
}
