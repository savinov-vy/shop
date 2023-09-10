package ru.savinov.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.savinov.shop.services.CartService;
import ru.savinov.shop.services.ProductService;
import ru.savinov.shop.test_helpers.factories.ProductFactory;
import ru.savinov.shop.test_helpers.factories.ShopFilterDtoFactory;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.savinov.shop.common.ConstantProperties.DEFAULT_PAGE_NUMBER;

import static ru.savinov.shop.common.ShopControllerConstant.PAGE;
import static ru.savinov.shop.common.ShopControllerConstant.PRODUCTS;
import static ru.savinov.shop.common.ShopControllerConstant.PRODUCT_FILTER;
import static ru.savinov.shop.common.ShopControllerConstant.TOTAL_PAGE;


@ExtendWith(SpringExtension.class)
class ShopControllerTest {

    ShopController subject;
    private MockMvc mvc;

    @Mock
    private ProductService productService;
    @Mock
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        subject = new ShopController(productService, cartService);
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    void shopPage() throws Exception {
        when(productService.getProducts(ShopFilterDtoFactory.of())).thenReturn(ProductFactory.ofFullList());
        ResultActions result = mvc.perform(get("/showcase")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr(PRODUCTS, ProductFactory.ofFullList())
                .flashAttr(PAGE, DEFAULT_PAGE_NUMBER)
                .flashAttr(TOTAL_PAGE, ProductFactory.ofFullList().size())
                .flashAttr(PRODUCT_FILTER, ShopFilterDtoFactory.of()));
        result
                .andExpect(status().isOk());
    }
}