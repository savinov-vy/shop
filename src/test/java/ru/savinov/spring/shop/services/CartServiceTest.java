package ru.savinov.spring.shop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.savinov.spring.test_helpers.factories.ProductDtoFactory;
import ru.savinov.spring.test_helpers.factories.ProductFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.savinov.spring.test_helpers.TestConstant.ONE_PRODUCT;


@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    ProductService productService;

    CartService subject;

    @BeforeEach
    void setUp() {
        subject = new CartService(productService);
        subject.setProductsDTO(ProductDtoFactory.ofProductsDto());
    }
    @Test
    void testGetSumPrice() {
        int expected = subject.getSumPrice();
        assertEquals(expected, 60);
    }

    @Test
    void testAddProductById() {
        Long productId = 4L;
        when(productService.getProductById(productId)).thenReturn(ProductFactory.ofProduct());
        Integer countBeforeAdd = subject.getProductsDTO().size();
        subject.addProductById(productId);
        Integer countAfterAdd = subject.getProductsDTO().size();
        assertEquals(countBeforeAdd + ONE_PRODUCT, countAfterAdd);
    }
}
