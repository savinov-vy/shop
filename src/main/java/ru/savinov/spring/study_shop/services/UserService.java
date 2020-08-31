package ru.savinov.spring.study_shop.services;

import ru.savinov.spring.study_shop.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
