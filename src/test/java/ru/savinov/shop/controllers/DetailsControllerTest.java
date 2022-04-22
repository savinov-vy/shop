package ru.savinov.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.savinov.shop.services.ProductService;
import ru.savinov.shop.test_helpers.factories.ProductFactory;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.savinov.shop.common.DetailsControllerConstant.SELECT_PRODUCT;
import static ru.savinov.shop.common.PageName.DETAILS_PAGE;


@ExtendWith(SpringExtension.class)
class DetailsControllerTest {

    DetailsController subject;
    private MockMvc mvc;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        subject = new DetailsController(productService);
        when(productService.getProductById(4L)).thenReturn(ProductFactory.ofOne());
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    void testDetailsPage() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/details/{id}", 4L)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
              .andExpect(status().isOk())
              .andExpect(view().name(DETAILS_PAGE))
              .andExpect(MockMvcResultMatchers.model().attribute(SELECT_PRODUCT, ProductFactory.ofOne()))
              .andExpect(MockMvcResultMatchers.model().size(1));
    }
}