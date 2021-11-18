package ru.savinov.shop.utils.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || !(authentication.getPrincipal() instanceof User) ? null : (User) authentication.getPrincipal();
    }


    public static User currentUser() {
        User currentCrmUser = getCurrentUser();

        if (currentCrmUser == null) {
            throw new AccessDeniedException("User is invalid or logged off");
        }
        return currentCrmUser;
    }

}
