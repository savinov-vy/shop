package ru.savinov.shop.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.savinov.shop.controllers.dto.UserWithRolesDTO;
import ru.savinov.shop.entities.Role;
import ru.savinov.shop.entities.User;
import ru.savinov.shop.repositories.RoleRepository;
import ru.savinov.shop.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Transactional
    public void save(User user) {
        user.setLogin(user.getLogin());
        user.setPassword("{noop}" + user.getPassword());
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public User createNewUser(User user) {
        String login = user.getLogin();
        String password = "{noop}" + user.getPassword();
        Boolean enabled = user.getEnabled();
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        return userRepository.save(User.of(
                login, password, enabled, roles
        ));
    }

    public List<UserWithRolesDTO> getAllUsersWithRoles() {
        List<UserWithRolesDTO> userWithRolesDTOList = new ArrayList<>();
        List<User> users = userRepository.findAll().stream()
                .sorted(Comparator.comparingLong(User::getId))
                .collect(Collectors.toList());
        for (User user : users) {
            Set<Role> setRoleUser = user.getRoles();
            String strAllRoleUser = "";
            for (Role role : setRoleUser) {
                strAllRoleUser += role.getName();
            }

            UserWithRolesDTO userDto = UserWithRolesDTO.builder()
                    .id(user.getId())
                    .login(user.getLogin())
                    .password(user.getPassword())
                    .enabled(user.getEnabled())
                    .roleName(strAllRoleUser)
                    .build();

            userWithRolesDTOList.add(userDto);
        }
        return userWithRolesDTOList;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void disableUser(Long id) {
        userRepository.desableUserById(id, false);
    }

    @Transactional
    public void enableUserById(Long id) {
        userRepository.enableUserById(id, true);
    }
}


