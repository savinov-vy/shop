package ru.savinov.spring.study_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//@EnableTransactionManagement
//@EnableJpaRepositories
@SpringBootApplication
public class StudyShopApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {

		SpringApplication.run(StudyShopApplication.class, args);

	}

}

