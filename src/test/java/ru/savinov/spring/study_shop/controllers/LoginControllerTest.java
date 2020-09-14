package ru.savinov.spring.study_shop.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;
    @Test
    public void contexLoads() throws Exception {
        assertThat(loginController).isNotNull();
    }
}
