package ru.savinov.spring.study_shop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Здесь указываются что пользователей необходимо брать из базы данных
    // и какие пользователи куда могут обращаться
    // необходимо здесь указать к какой базе данных подключаться и где брать пользователей
    // в spring за подключение отвечает бин dataSource
    // сам бин dataSource сконфигурирован в application.properties
    // сюда мы его инжектим

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // теперь нужно сконфигурировать способ аутентификации
    // аутентификацию будем производить через базу данных
    // а само подключение к базе берем через ссылку на источник данных dataSource
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    //настройки как кому переходить можно и нельзя

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/details/**").hasAnyRole("ADMIN") // но по ссылке детали и дальше по маске ** может ходить только пользователь имеющий роль АДМИН причем здесь нужно указать без префикса ROL_ как в базе данных.
                .anyRequest().permitAll()  //сначала мы даём разрешение чтобы ходили все везде
                .and().formLogin().loginPage("/login").permitAll() // для логина мы используем форму логина для этого нужно постучаться на /login  для формы логина даем доступ всем
                .loginProcessingUrl("/authenticateTheUser");  // для проверки корректности мы используем специальный URL
    }
}
