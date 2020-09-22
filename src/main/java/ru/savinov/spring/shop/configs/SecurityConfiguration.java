package ru.savinov.spring.shop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
        //.passwordEncoder(passwordEncoder());
    }

    //TODO тут
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/details/**").hasAnyRole("ADMIN") // но по ссылке детали и дальше по маске ** может ходить только пользователь имеющий роль АДМИН причем здесь нужно указать без префикса ROL_ как в базе данных.
                .anyRequest().permitAll()  //сначала мы даём разрешение чтобы ходили все везде
                .and().formLogin().loginPage("/login").permitAll() // для логина мы используем форму логина для этого нужно постучаться на /login  для формы логина даем доступ всем
                .loginProcessingUrl("/authenticateTheUser");  // для проверки корректности мы используем специальный URL


    }


}
