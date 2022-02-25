package ru.savinov.shop.utils.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.savinov.shop.entities.Role;
import ru.savinov.shop.entities.User;

import java.util.Set;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public void removeProductByCount(Integer))")
    public void beforeRemoveProductByCountAdvice() {
        System.out.println("beforeRemoveProductByCountAdvice: попытка удалить продукт из корзины");
    }

    @After("execution(public void createNewUser(ru.savinov.shop.entities.User))")
    public void afterCreateNewUserAdvice() {
        System.out.println("afterCreateNewUserAdvice: создан новый пользователь");
    }

    @After("execution(public void save(..))")
    public void afterSaveAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("afterSaveAdvice method: " + methodSignature.getMethod());
        System.out.println("afterSaveAdvice return type: " + methodSignature.getReturnType());
        System.out.println("afterSaveAdvice method name: " + methodSignature.getName());

        System.out.println("************************************************************");

        Object [] arguments = joinPoint.getArgs();
        for (Object obj : arguments) {
            if (obj instanceof User) {
                User user = (User) obj;
                System.out.println("User login: " + user.getLogin());
                System.out.println("User id: " + user.getId());
                System.out.println("User password: " + user.getPassword());
                System.out.println("User enabled " + user.getEnabled());
                Set<Role> roles = user.getRoles();

                for (Role role : roles) {
                    System.out.println("role name: " + role.getName());
                    System.out.println("role id: " + role.getId());

                }
            }
        }
    }
}
