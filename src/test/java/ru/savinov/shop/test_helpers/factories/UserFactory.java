package ru.savinov.shop.test_helpers.factories;

import ru.savinov.shop.entities.User;

public class UserFactory {

    public static User of() {
        User user = User.of();
        user.setLogin("testLogin");
        return user;
    }
}
