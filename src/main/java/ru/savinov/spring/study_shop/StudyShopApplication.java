package ru.savinov.spring.study_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication //(scanBasePackages = {"ru.savinov.spring.study_shop"}, exclude = JpaRepositoriesAutoConfiguration.class)
public class StudyShopApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(StudyShopApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StudyShopApplication.class);
	}*/
}

