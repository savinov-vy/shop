package ru.savinov.spring.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter   //<- чтобы idea не ругалась кроме библиотеки ломбок нужно подключить плагин ломбок к IDE
@ToString
public class UserWithRoles {
    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String roleName;
}
