package ru.savinov.shop.utils.security;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ru.savinov.shop.services.UserService;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class SecurityUtils {

    UserService userService;

    public ru.savinov.shop.entities.User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authentication == null || !(authentication.getPrincipal() instanceof User) ? null : (User) authentication.getPrincipal();
        if (isNull(user)) {
            throw new AccessDeniedException("User is invalid or logged off");
        }

        return  userService.findByLogin(user.getUsername());
    }
}
