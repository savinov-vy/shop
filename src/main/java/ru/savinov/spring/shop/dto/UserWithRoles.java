package ru.savinov.spring.shop.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class UserWithRoles {
    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String roles;
}
