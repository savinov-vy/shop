package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.savinov.shop.controllers.dto.UserDto;
import ru.savinov.shop.entities.User;
import ru.savinov.shop.services.UserService;
import ru.savinov.shop.services.UserValidator;


import javax.validation.Valid;

import static ru.savinov.shop.common.PageName.REDIRECT_LOGIN_PAGE;
import static ru.savinov.shop.common.PageName.REGISTRATION_PAGE;
import static ru.savinov.shop.common.RegistrationControllerConstant.ERROR_DESCRIPTION;
import static ru.savinov.shop.common.RegistrationControllerConstant.USER_FORM;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    private UserValidator userValidator;

    @GetMapping("/reg")
    public String registration(Model model) {
        model.addAttribute(USER_FORM, UserDto.of());
        return REGISTRATION_PAGE;
    }

    @PostMapping(value = "/reg/form")
    public String addUser(@Valid @ModelAttribute("user") UserDto userForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute(ERROR_DESCRIPTION, userValidator.getErrorDescribtion());
            model.addAttribute(USER_FORM, userForm);
            return REGISTRATION_PAGE;
        }

        userService.save(User.of(userForm));
        redirectAttrs.addFlashAttribute("user", User.of(userForm));
        return REDIRECT_LOGIN_PAGE;
    }
}
