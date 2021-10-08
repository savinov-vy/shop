package ru.savinov.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserWithRolesDTO {
    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String roleName;
}
