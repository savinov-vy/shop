package ru.savinov.spring.study_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.study_shop.entities.User;
import ru.savinov.spring.study_shop.repositories.RoleRepository;
import ru.savinov.spring.study_shop.repositories.UserRepository;
import ru.savinov.spring.study_shop.services.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {


    private UserService userService;

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
    public String addUser(@ModelAttribute("user") @Valid User userForm /*,BindingResult bindingResult, Model model*/) {

/*        userService.createNewUser(
                user.getUsername(),
                user.getPassword());

        if (bindingResult.hasErrors()) {
            return "reg";
        }
       if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "reg";
        }*/
        userService.save(userForm);

        return "redirect:/";
    }
}
