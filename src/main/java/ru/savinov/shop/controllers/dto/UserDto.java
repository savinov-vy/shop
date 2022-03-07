package ru.savinov.shop.controllers.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(staticName = "of")
public class UserDto {

    @NotNull
    @Length(min = 3)
    private String login;

    @NotNull
    @Length(min = 1)
    private String password;

    @NotNull
    @Length(min = 1)
    private String confirmPassword;

    private Boolean enabled;

}
