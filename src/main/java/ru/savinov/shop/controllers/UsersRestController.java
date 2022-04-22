package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.savinov.shop.controllers.dto.UserWithRolesDTO;
import ru.savinov.shop.entities.User;
import ru.savinov.shop.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
public class UsersRestController {

    private final UserService userService;

    @GetMapping("/user")
    public List<UserWithRolesDTO> getUsers() {
        List<UserWithRolesDTO> allUsersWithRoles = userService.getAllUsersWithRoles();
        return allUsersWithRoles;
    }

    @PostMapping("/delete_user")
    public User deleteUser(@RequestBody User user) {
        Long id = user.getId();
        userService.deleteUserById(id);
        return user;
    }

    @PostMapping("/add_user")
    public void addUser(@RequestBody User user, HttpServletResponse response) throws IOException {
        userService.createNewUser(user);
        response.sendRedirect("/shop/user");
    }

    @PostMapping("desable_user")
    public User offUser(@RequestBody User user) {
        Long id = user.getId();
        userService.disableUser(id);
        return user;
    }

    @PostMapping("enable_user")
    public User onUser(@RequestBody User user) {
        Long id = user.getId();
        userService.enableUserById(id);
        return user;
    }
}
