package ru.savinov.spring.study_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.savinov.spring.study_shop.entities.Role;
import ru.savinov.spring.study_shop.entities.User;
import ru.savinov.spring.study_shop.repositories.RoleRepository;
import ru.savinov.spring.study_shop.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class UserService {
    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private UserRepository userDao;

    public void save(User user) {
        user.setPassword(user.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
