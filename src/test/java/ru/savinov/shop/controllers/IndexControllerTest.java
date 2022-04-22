package ru.savinov.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.savinov.shop.common.PageName.INDEX_PAGE;

@ExtendWith(SpringExtension.class)
class IndexControllerTest {

    private IndexController subject;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        subject = new IndexController();
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    void testIndex() throws Exception {
        mvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name(INDEX_PAGE));
    }
}
