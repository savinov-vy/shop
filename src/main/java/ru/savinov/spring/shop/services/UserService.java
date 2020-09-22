package ru.savinov.spring.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savinov.spring.shop.entities.Role;
import ru.savinov.spring.shop.entities.User;
import ru.savinov.spring.shop.repositories.RoleRepository;
import ru.savinov.spring.shop.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        user.setUsername(user.getUsername());
        user.setPassword("{noop}" + user.getPassword());
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createNewUser(User user) {
        String username = user.getUsername();
        String password = "{noop}" + user.getPassword();
        Boolean enabled = true;
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));

    }


}


