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
import ru.savinov.shop.controllers.dto.ProductDTO;
import ru.savinov.shop.services.CartService;
import ru.savinov.shop.test_helpers.factories.ProductFactory;
import ru.savinov.shop.test_helpers.factories.UserFactory;
import ru.savinov.shop.utils.security.SecurityUtils;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.savinov.shop.common.CartControllerConstant.TOTAL_PRICE;
import static ru.savinov.shop.common.PageName.CART_PAGE;
import static ru.savinov.shop.common.ShopControllerConstant.PRODUCTS;

@ExtendWith(SpringExtension.class)
class CartControllerTest {

    @Mock
    private CartService cartService;

    @Mock
    private SecurityUtils securityUtils;

    CartController subject;
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        subject = new CartController(cartService, securityUtils);
        when(cartService.getProducts()).thenReturn(ProductFactory.ofProducts());
        when(securityUtils.getCurrentUser()).thenReturn(UserFactory.of());
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }


    @Test
    void testDetailsPage() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/my-basket")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(status().isOk())
                .andExpect(view().name(CART_PAGE))
                .andExpect(MockMvcResultMatchers.model().attribute(PRODUCTS, ProductDTO.of(ProductFactory.ofProducts())))
                .andExpect(MockMvcResultMatchers.model().attribute(TOTAL_PRICE, CartService.getSumPrice(ProductFactory.ofProducts())))
                .andExpect(MockMvcResultMatchers.model().size(2));
    }
}