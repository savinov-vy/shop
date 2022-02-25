package ru.savinov.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.savinov.shop.entities.User;

@Component
public class UserValidator implements Validator {

    private String errorDescribtion = "";

    public String getErrorDescribtion() {
        return errorDescribtion;
    }

    public void setErrorDescribtion(String errorDescribtion) {
        this.errorDescribtion = errorDescribtion;
    }

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (user.getLogin().length() < 3) {
            errors.rejectValue("login", "Size.userForm.username");
            errorDescribtion = "Логин не должен быть меньше 3 символов";
        }

        if (userService.findByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.userForm.username");
            errorDescribtion = "Пользователь с таким именем уже зарегистрирован";
        }
    }
}
