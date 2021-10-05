package ru.savinov.spring.shop.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ru.savinov.spring.shop.common.RoleType;
import ru.savinov.spring.shop.services.UserDetailsServiceImpl;

import javax.sql.DataSource;

import static ru.savinov.spring.shop.common.PageName.LOGIN_PAGE_URL;
import static ru.savinov.spring.shop.common.PageName.LOGIN_PROCESSING_URL;
import static ru.savinov.spring.shop.common.PageName.SHOP_PAGE_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Integer PERIOD_VALIDITY_TOKEN = 300;
    private final String KEY_WORD = "something";


    private UserDetailsServiceImpl userDetailsService;

    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
        //.passwordEncoder(passwordEncoder());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/details/**").hasAnyRole(RoleType.ADMIN.getLabel())
                .antMatchers("/users_control/**").hasAnyRole(RoleType.ADMIN.getLabel())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .failureUrl(SHOP_PAGE_URL)
                .loginPage(LOGIN_PAGE_URL)
                .permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .key(KEY_WORD)
                .tokenValiditySeconds(PERIOD_VALIDITY_TOKEN);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
