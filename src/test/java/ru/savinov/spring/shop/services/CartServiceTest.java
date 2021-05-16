package ru.savinov.spring.shop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.savinov.spring.shop.dto.ProductDTO;
import ru.savinov.spring.shop.entities.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    private List<ProductDTO> productsDTO;
    Product apple;
    Product orange;
    Product banana;

    CartService subject;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        subject = new CartService(productService);
    }

    @Test
    void testGetSumPrice() {
        apple = new Product(1L,"apple", 10);
        orange = new Product(2L,"orange", 20);
        banana = new Product(3L, "banana", 30);
        when(productService.getProductById(1L)).thenReturn(apple);
        when(productService.getProductById(2L)).thenReturn(orange);
        when(productService.getProductById(3L)).thenReturn(banana);
        subject.addProductById(1L);
        subject.addProductById(2L);
        subject.addProductById(3L);
        assertEquals(60, subject.getSumPrice());
    }

    @Test
    void getProductsDTO() {
    }

    @Test
    void init() {
    }

    @Test
    void addProductById() {
    }

    @Test
    void removeProductByCount() {
    }
}