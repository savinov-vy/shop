package ru.savinov.spring.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class UserWithRoles {
    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String roleName;
}
