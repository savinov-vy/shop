package ru.savinov.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.savinov.shop.controllers.dto.UserDto;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@ToString
@NoArgsConstructor()
@AllArgsConstructor(staticName = "of")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;


    public static User of(String login, String password, Boolean enabled, Set<Role> roles) {
        return of(null, login, password, enabled, roles);
    }

    public static User of(UserDto userDto) {
        return User.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .enabled(userDto.getEnabled())
                .build();
    }

    public static User of() {
        return new User();
    }
}