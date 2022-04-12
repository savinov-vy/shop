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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.savinov.shop.test_helpers.TestConstant.ADDED_PRODUCT_ID;
import static ru.savinov.shop.test_helpers.TestConstant.CART_PAGE_URL;
import static ru.savinov.shop.test_helpers.TestConstant.REMOVED_PRODUCT_ID;
import static ru.savinov.shop.test_helpers.TestConstant.SHOP_PAGE_URL;
import static ru.savinov.shop.test_helpers.TestConstant.TOTAL_PRICE;
import static ru.savinov.shop.test_helpers.TestConstant.PRODUCTS;
import static ru.savinov.shop.test_helpers.TestConstant.CART_PAGE;

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
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    void testShowCart() throws Exception {
        when(cartService.getProducts()).thenReturn(ProductFactory.ofFullList());
        when(securityUtils.getCurrentUser()).thenReturn(UserFactory.of());

        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/my-basket")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(status().isOk())
                .andExpect(view().name(CART_PAGE))
                .andExpect(MockMvcResultMatchers.model().attribute(PRODUCTS, ProductDTO.of(ProductFactory.ofFullList())))
                .andExpect(MockMvcResultMatchers.model().attribute(TOTAL_PRICE, CartService.getSumPrice(ProductFactory.ofFullList())))
                .andExpect(MockMvcResultMatchers.model().size(2));
    }

    @Test
    void testAddProductToCart() throws Exception {

        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/my-basket/add/{Id}", ADDED_PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(MockMvcResultMatchers.redirectedUrl(SHOP_PAGE_URL))
                .andExpect(status().isFound());

        verify(cartService).addProduct(ADDED_PRODUCT_ID);
    }

    @Test
    void testRemoveByIdFromCart() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/my-basket/remove/{Id}", REMOVED_PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(MockMvcResultMatchers.redirectedUrl(CART_PAGE_URL))
                .andExpect(status().isFound());

        verify(cartService).removeProductById(REMOVED_PRODUCT_ID);
    }
}