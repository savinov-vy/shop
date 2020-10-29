package ru.savinov.spring.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebProductControllerA {

    @GetMapping("/a")
    public String getAjax(){
        return "ajax";
    }
}
