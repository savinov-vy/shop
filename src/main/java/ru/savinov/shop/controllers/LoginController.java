package ru.savinov.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static ru.savinov.shop.common.PageName.LOGIN_PAGE;

@Controller
public class LoginController {

    @GetMapping("/sign")
    public ModelAndView login() {
        return new ModelAndView(LOGIN_PAGE);
    }
}
