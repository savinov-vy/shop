package ru.savinov.spring.study_shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.savinov.spring.study_shop.entities.Product;
import ru.savinov.spring.study_shop.services.ProductService;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
class DetailsControllerTest {

    @Mock
    ProductService productService;

    private MockMvc mvc;
    private DetailsController subject;
    private Product product;

    @BeforeEach
    void beforeEach() {
        subject = new DetailsController(productService);
        product = new Product(1L, "Ball", 50);
        mvc = MockMvcBuilders
                .standaloneSetup(subject)
                .build();
    }

    @Test
    public void getDetailsPage() throws Exception {

        Mockito.when(productService.getProductById(1L)).thenReturn(product);

        mvc.perform(get("/details/1")).andExpect(status().isOk())
                .andExpect(view().name("details.html"))
                .andExpect(model().attributeExists("selectProduct"))
                .andExpect(model().attribute("selectProduct", hasProperty("id", is(1L))))
                .andExpect(model().attribute("selectProduct", hasProperty("title", is("Ball"))))
                .andExpect(model().attribute("selectProduct", hasProperty("price", is(50))));

    }
}




