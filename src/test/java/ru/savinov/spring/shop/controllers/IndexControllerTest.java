package ru.savinov.spring.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    private IndexController subject;

    private MockMvc mvc;

    @BeforeEach
    void beforeEach() {
        subject = new IndexController();
        mvc = MockMvcBuilders
                .standaloneSetup(subject)
                .build();
    }

    @Test
    public void getIndexPage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/index");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("index.html"));
    }
}