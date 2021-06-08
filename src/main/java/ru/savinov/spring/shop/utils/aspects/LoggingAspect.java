package ru.savinov.spring.shop.utils.aspects;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public void removeProductByCount(Integer))")
    public void beforeRemoveProductByCountAdvice() {
        System.out.println("beforeRemoveProductByCountAdvice: попытка удалить продукт из корзины");
    }

    @After("execution(public void createNewUser(ru.savinov.spring.shop.entities.User))")
    public void afterCreateNewUserAdvice() {
        System.out.println("afterCreateNewUserAdvice: создан новый пользователь");
    }
}
