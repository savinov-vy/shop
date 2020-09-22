package ru.savinov.spring.shop.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LoginControllerTest {

    private LoginController subject;

    private MockMvc mvc;

    @BeforeEach
    void beforeEach() {
        subject = new LoginController();
        mvc = MockMvcBuilders
                .standaloneSetup(subject)
                .build();
    }

    @Test
    public void getLoginPage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/login");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("login.html" ));
    }
}
