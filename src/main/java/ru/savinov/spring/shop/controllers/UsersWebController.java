package ru.savinov.spring.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersWebController {

    @GetMapping("/users")
    public String users() {
        return "users";
    }

}
