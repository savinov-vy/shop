package ru.savinov.spring.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savinov.spring.shop.dto.UserWithRoles;
import ru.savinov.spring.shop.entities.Role;
import ru.savinov.spring.shop.entities.User;
import ru.savinov.spring.shop.repositories.RoleRepository;
import ru.savinov.spring.shop.repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(User user) {
        user.setUsername(user.getUsername());
        user.setPassword("{noop}" + user.getPassword());
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void createNewUser(User user) {
        String username = user.getUsername();
        String password = "{noop}" + user.getPassword();
        Boolean enabled = true;
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));

    }

    public List<UserWithRoles> getAllUsersWithRoles() {
        List<UserWithRoles> userWithRolesList = new ArrayList<>();
        UserWithRoles userWithRoles = new UserWithRoles();
        List<User> userList = new ArrayList<>();
        userList.addAll(userRepository.findAll());

        for (User user : userList) {
            userWithRoles.setEnabled(user.getEnabled());
            userWithRoles.setId(user.getId());
            userWithRoles.setPassword(user.getPassword());
            userWithRoles.setUsername(user.getUsername());
            userWithRoles.setRoles(user.getRoles().stream().map(r -> r.getName().join("/n")).toString());
            userWithRolesList.add(userWithRoles);
        }
        return userWithRolesList;
    }



}


