package ru.savinov.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static ru.savinov.shop.common.PageName.INDEX_PAGE;

@Controller
public class IndexController {

    @GetMapping("/home")
    public ModelAndView homePage() {
        return new ModelAndView(INDEX_PAGE);
    }
}
