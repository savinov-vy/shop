package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.savinov.shop.dto.UserWithRolesDTO;
import ru.savinov.shop.entities.User;
import ru.savinov.shop.services.UserService;
import java.util.List;


@RestController
@AllArgsConstructor
public class UsersRestController {

    private UserService userService;

    @GetMapping("/user")
    public List<UserWithRolesDTO> getUser() {
        return userService.getAllUsersWithRoles();
    }

    @PostMapping("/delete_user")
    public @ResponseBody User deleteUser(@RequestBody User user) {
        Long id = user.getId();
        userService.deleteUserById(id);
        return user;
    }

    @PostMapping("/add_user")
    public @ResponseBody User addUser(@RequestBody User user) {
        userService.createNewUser(user);
        return user;
    }

    @PostMapping("desable_user")
    public @ResponseBody User desableUser(@RequestBody User user) {
        Long id = user.getId();
        userService.desableUser(id);
        return user;
    }

    @PostMapping("enable_user")
    public @ResponseBody User enableUser(@RequestBody User user) {
        Long id = user.getId();
        userService.enableUserById(id);
        return user;
    }
}
