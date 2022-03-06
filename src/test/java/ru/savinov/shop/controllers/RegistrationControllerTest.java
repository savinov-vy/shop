package ru.savinov.shop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import ru.savinov.shop.config.AbstractWebMvcSpringBootTest;
import ru.savinov.shop.services.UserService;
import ru.savinov.shop.services.UserValidator;
import ru.savinov.shop.test_helpers.factories.UserFactory;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.savinov.shop.common.RegistrationControllerConstant.USER_FORM;
import static ru.savinov.shop.test_helpers.TestConstant.LOGIN_PAGE_URL;
import static ru.savinov.shop.test_helpers.TestConstant.REGISTRATION_PAGE;


@ExtendWith(SpringExtension.class)
@WebMvcTest(
        value = RegistrationController.class,
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        value = RegistrationController.class
                )
        })
class RegistrationControllerTest extends AbstractWebMvcSpringBootTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserValidator userValidator;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void testAddUser__redirect() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.post("/reg/form")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(status().isFound());
    }

    @Test
    void testAddUser() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.post("/reg/form")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserFactory.ofReg()))
        );
        result
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl(LOGIN_PAGE_URL));
    }
}