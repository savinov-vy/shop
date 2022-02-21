package ru.savinov.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LoginControllerTest {
    private LoginController subject;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        subject = new LoginController();
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    void testIndex() throws Exception {
        mvc.perform(get("/sign"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}