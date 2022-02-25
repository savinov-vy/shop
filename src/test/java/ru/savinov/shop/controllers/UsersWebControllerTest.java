package ru.savinov.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.savinov.shop.common.PageName.USERS_CONTROL_PAGE;

class UsersWebControllerTest {
    private UsersWebController subject;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        subject = new UsersWebController();
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    void testIndex() throws Exception {
        mvc.perform(get("/users-control"))
                .andExpect(status().isOk())
                .andExpect(view().name(USERS_CONTROL_PAGE));
    }
}