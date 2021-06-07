package ru.savinov.spring.shop.utils.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public void removeProductByCount(Integer))")
    public void beforeRemoveProductByCountAdvice() {
        System.out.println("beforeRemoveProductByCountAdvice: попытка удалить продукт из корзины");
    }
}
