package ru.savinov.spring.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static ru.savinov.spring.shop.common.PageName.USERS_CONTROL_PAGE;

@Controller
public class UsersWebController {

    @GetMapping("/users_control")
    public String users() {

        return USERS_CONTROL_PAGE;
    }
}
