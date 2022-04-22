package ru.savinov.shop.configs;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class MyConfig {
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
