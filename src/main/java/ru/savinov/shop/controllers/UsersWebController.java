package ru.savinov.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static ru.savinov.shop.common.PageName.USERS_CONTROL_PAGE;

@Controller
public class UsersWebController {

    @GetMapping("/users-control")
    public String users() {
        return USERS_CONTROL_PAGE;
    }
}
