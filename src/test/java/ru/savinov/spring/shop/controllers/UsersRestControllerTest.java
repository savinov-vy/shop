package ru.savinov.spring.shop.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.shop.config.AbstractWebMvcSpringBootTest;
import ru.savinov.spring.shop.dto.UserWithRolesDTO;
import ru.savinov.spring.shop.entities.User;
import ru.savinov.spring.shop.services.UserService;
import ru.savinov.spring.test_helpers.factories.UserFactory;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsersRestController.class)
class UsersRestControllerTest extends AbstractWebMvcSpringBootTest {

    @MockBean
    UserService userService;

    @BeforeEach
    void init() {
//        userService
    }

    @Test
    void getUser() {
//        when(userService.getAllUsersWithRoles()).thenReturn(UserFactory.ofUserWithRoles());
    }

//    @Test
//    void getVisitedDomains() throws Exception {
//        System.out.println(DomainResponse.of(visitService.getByFilter(visitFilterDto), Properties.STATUS_OK));
//        ResultActions result = mockMvc.perform(
//                MockMvcRequestBuilders.get("/visited_domains")
//                        .param("from", String.valueOf(visitFilterDto.getFrom()))
//                        .param("to", String.valueOf(visitFilterDto.getTo()))
//                        .content(objectMapper.writeValueAsString(DomainResponse.of(visitService.getByFilter(visitFilterDto), Properties.STATUS_OK)))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//        result
//                .andExpect(jsonPath("$.domains", hasSize(3)))
//                .andExpect(jsonPath("$.status", Matchers.equalTo(Properties.STATUS_OK)))
//                .andExpect(status().isOk());
//    }

    @Test
    void deleteUser() {
    }

    @Test
    void addUser() {
    }

    @Test
    void desableUser() {
    }

    @Test
    void enableUser() {
    }
}
/*
@RestController
public class UsersRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<UserWithRolesDTO> getTestUser() {
        List<UserWithRolesDTO> list = userService.getAllUsersWithRoles();
        System.out.println(list);
        return list;
    }
    @PostMapping("/delete_user")
    public @ResponseBody
    User deleteUser(@RequestBody User user) {
        Long id = user.getId();
        userService.deleteUserById(id);
        return user;
    }
    @PostMapping("/add_user")
    public @ResponseBody User addUser(@RequestBody User user) {
        userService.createNewUser(user);
        return user;
    }

    @PostMapping("desable_user")
    public @ResponseBody User desableUser(@RequestBody User user) {
        Long id = user.getId();
        userService.desableUser(id);
        return user;
    }
    @PostMapping("enable_user")
    public @ResponseBody User enableUser(@RequestBody User user) {
        Long id = user.getId();
        userService.enableUserById(id);
        return user;
    }
}*/
