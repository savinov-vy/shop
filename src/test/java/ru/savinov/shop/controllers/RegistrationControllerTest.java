package ru.savinov.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.savinov.shop.config.AbstractWebMvcSpringBootTest;
import ru.savinov.shop.controllers.dto.UserDto;
import ru.savinov.shop.services.UserService;
import ru.savinov.shop.services.UserValidator;
import ru.savinov.shop.test_helpers.factories.UserDtoFactory;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.savinov.shop.common.RegistrationControllerConstant.USER_FORM;
import static ru.savinov.shop.test_helpers.TestConstant.LOGIN_PAGE_URL;
import static ru.savinov.shop.test_helpers.TestConstant.REGISTRATION_PAGE;


@Import(UserValidator.class)
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

    @Autowired
    private UserValidator userValidator;

    RegistrationController subject;
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        subject = new RegistrationController(userService, userValidator);
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }


    @Test
    void registration__getForm() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/reg")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTRATION_PAGE))
                .andExpect(model().attribute(USER_FORM, UserDtoFactory.of()))
                .andExpect(model().size(1));
    }

    @Test
    void addUser_save() throws Exception {
        ResultActions result = mvc.perform(post("/reg/form")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("user", UserDtoFactory.ofReg()));
        result
                .andExpect(model().errorCount(0))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl(LOGIN_PAGE_URL));
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void addUser__returnRegistryForm(Integer errorCount, UserDto userDto) throws Exception {
        ResultActions result = mvc.perform(post("/reg/form")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("user", userDto)
        );
        result
                .andExpect(model().errorCount(errorCount))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTRATION_PAGE));
    }

    public static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(3, UserDtoFactory.emptyLogin()),
                Arguments.of(1,  UserDtoFactory.notConfirmPassword()),
                Arguments.of(2, UserDtoFactory.shortLogin()),
                Arguments.of(2, UserDtoFactory.emptyPassword()),
                Arguments.of(6, UserDtoFactory.of())
        );
    }
}