package ru.savinov.shop.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.savinov.shop.AbstractTest;
import ru.savinov.shop.services.ProductService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class ShopControllerTest extends AbstractTest {



//    @Autowired
//    private ShopController subject;


    @Test
    public void testShopPage() throws Exception {
//        assertNotNull(subject);
    }
}
