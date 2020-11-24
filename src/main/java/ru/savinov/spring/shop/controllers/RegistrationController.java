package ru.savinov.spring.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.shop.entities.User;
import ru.savinov.spring.shop.services.UserService;
import ru.savinov.spring.shop.services.UserValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "reg";
    }

    @PostMapping("/registration/form")
    public String addUser(@ModelAttribute("user") @Valid User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if(bindingResult.hasErrors()){
            return "reg";
        }

        userService.save(userForm);

        return "redirect:/";
    }
}
