package ru.savinov.spring.shop.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.savinov.spring.shop.dto.UserWithRolesDTO;
import ru.savinov.spring.shop.entities.Role;
import ru.savinov.spring.shop.entities.User;
import ru.savinov.spring.shop.repositories.RoleRepository;
import ru.savinov.spring.shop.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private RoleRepository roleRepository;
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
        Boolean enabled = user.getEnabled();
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        userRepository.save(new User(
                username, password, enabled, roles
        ));
    }

    public List<UserWithRolesDTO> getAllUsersWithRoles() {
        List<UserWithRolesDTO> userWithRolesDTOList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        userList = userRepository.findAll();
        for (User user : userList) {
            UserWithRolesDTO userWithRolesDTO = new UserWithRolesDTO();
            userWithRolesDTO.setEnabled(user.getEnabled());
            userWithRolesDTO.setId(user.getId());
            userWithRolesDTO.setPassword(user.getPassword());
            userWithRolesDTO.setUsername(user.getUsername());
            Set<Role> setRoleUser = user.getRoles();
            String strAllRoleUser = "";
            for (Role role : setRoleUser) {
                strAllRoleUser += role.getName();
            }
            userWithRolesDTO.setRoleName(strAllRoleUser);

            userWithRolesDTOList.add(userWithRolesDTO);
        }
        return userWithRolesDTOList;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

    @Transactional
    public void desableUser(Long id) {
        userRepository.desableUserById(id, false);
    }

    @Transactional
    public void enableUserById(Long id) {
        userRepository.enableUserById(id, true);
    }
}


