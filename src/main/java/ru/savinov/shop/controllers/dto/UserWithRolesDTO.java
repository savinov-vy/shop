package ru.savinov.shop.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserWithRolesDTO {

    private Long id;
    private String login;
    private String password;
    private Boolean enabled;
    private String roleName;
}
