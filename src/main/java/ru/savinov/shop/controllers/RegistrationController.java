package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.savinov.shop.entities.User;
import ru.savinov.shop.services.UserService;
import ru.savinov.shop.services.UserValidator;

import javax.validation.Valid;

import static ru.savinov.shop.common.PageName.REDIRECT_ROOT_APP;
import static ru.savinov.shop.common.PageName.REGISTRATION_PAGE;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    private UserValidator userValidator;

    private static final String ERROR_DESCRIPTION = "errorDescribtion";
    private static final String USER_FORM = "user";

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute(USER_FORM, new User());
        return REGISTRATION_PAGE;
    }

    @PostMapping("/registration/form")
    public String addUser(@Valid User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute(ERROR_DESCRIPTION, userValidator.getErrorDescribtion());
            return REGISTRATION_PAGE;
        }

        userService.save(userForm);
        return REDIRECT_ROOT_APP;
    }
}