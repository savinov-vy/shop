package ru.savinov.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.savinov.shop.controllers.dto.UserDto;
import ru.savinov.shop.entities.User;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static ru.savinov.shop.common.ConstantProperties.MIN_COUNT_CHARS;

@Component
public class UserValidator implements Validator {

    private String errorDescribtion;

    public String getErrorDescribtion() {
        return errorDescribtion;
    }

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        errorDescribtion = "";
        UserDto userDto = (UserDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        boolean isValidLogin = isNotBlank(userDto.getLogin())
                && userDto.getLogin().length() > MIN_COUNT_CHARS;

        if (!isValidLogin) {
            errors.rejectValue("login", "Size.userForm.username");
            errorDescribtion = "Логин должен быть не меньше 3 символов";
        }

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            errors.rejectValue("password", "DontEquals.userForm.confirmPassword");
            errorDescribtion = "Пароль и подтверждение пароля не совпадают";
        }

        if (!isNotBlank(userDto.getPassword())) {
            errors.rejectValue("password", "Size.userForm.password");
            errorDescribtion = "Пароль должен быть задан";
        }

        if (nonNull(userService.findByLogin(userDto.getLogin()))) {
            errors.rejectValue("login", "Duplicate.userForm.username");
            errorDescribtion = "Пользователь с таким именем уже зарегистрирован";
        }
    }
}
