package ru.savinov.spring.study_shop.services;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
