package ru.savinov.shop.controllers.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
public class UserDto {

    private String login;

    private String password;

    private String confirmPassword;

    private Boolean enabled;

}
