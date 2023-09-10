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
import ru.savinov.shop.services.CartService;
import ru.savinov.shop.services.KafkaProducerService;
import ru.savinov.shop.utils.security.SecurityUtils;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.savinov.shop.test_helpers.TestConstant.CART_PAGE_URL;
import static ru.savinov.shop.test_helpers.TestConstant.REMOVED_PRODUCT_ID;

@ExtendWith(SpringExtension.class)
class CartControllerTest {

    @Mock
    private CartService cartService;
    @Mock
    private SecurityUtils securityUtils;
    @Mock
    private KafkaProducerService kafkaProducerService;

    CartController subject;
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        subject = new CartController(cartService, securityUtils, kafkaProducerService);
        mvc = MockMvcBuilders.standaloneSetup(subject).build();
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