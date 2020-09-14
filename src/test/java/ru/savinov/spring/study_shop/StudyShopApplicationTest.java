package ru.savinov.spring.study_shop;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.savinov.spring.study_shop.controllers.LoginController;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class StudyShopApplicationTest {

//	@MockBean
//	private LoginController controller;

	@Test
	void contextLoads() {
	//	assertThat(controller).isNotNull();

	}

}
