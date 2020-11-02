package ru.savinov.spring.shop.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.shop.dto.UserWithRoles;
import ru.savinov.spring.shop.entities.User;
import ru.savinov.spring.shop.repositories.UserRepository;
import ru.savinov.spring.shop.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersRestController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/user")
    public @ResponseBody List<UserWithRoles> getTestUser() {
        return userService.getAllUsersWithRoles();
    }

    @PostMapping("/add_user")
    public @ResponseBody User addUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }
}
