package ru.savinov.shop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.savinov.shop.config.AbstractWebMvcSpringBootTest;
import ru.savinov.shop.services.ProductService;
import ru.savinov.shop.test_helpers.factories.ProductFactory;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ExtendWith(SpringExtension.class)
class DetailsControllerTest extends AbstractWebMvcSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ProductService productService;

    DetailsController subject;
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        subject = new DetailsController(productService);
        when(productService.getProductById(4L)).thenReturn(ProductFactory.ofProduct());
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }


    @Test
    void testDetailsPage() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/details/{id}", 4L)
                        .content(objectMapper.writeValueAsString(ProductFactory.ofProduct()))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
              .andExpect(status().isOk())
              .andExpect(view().name("details"));
    }
}