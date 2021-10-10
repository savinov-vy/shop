package ru.savinov.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static ru.savinov.shop.common.PageName.LOGIN_PAGE;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView(LOGIN_PAGE);
        return mav;
    }
}