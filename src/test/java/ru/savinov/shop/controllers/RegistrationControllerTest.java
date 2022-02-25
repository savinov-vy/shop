package ru.savinov.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.savinov.shop.services.UserService;
import ru.savinov.shop.services.UserValidator;
import ru.savinov.shop.test_helpers.factories.UserFactory;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.savinov.shop.common.PageName.REGISTRATION_PAGE;
import static ru.savinov.shop.common.RegistrationControllerConstant.USER_FORM;


@ExtendWith(SpringExtension.class)
class RegistrationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserValidator userValidator;

    RegistrationController subject;
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        subject = new RegistrationController(userService, userValidator);
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }


    @Test
    void testRegistration() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/reg")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTRATION_PAGE))
                .andExpect(MockMvcResultMatchers.model().attribute(USER_FORM, UserFactory.of()))
                .andExpect(MockMvcResultMatchers.model().size(1));
    }
}