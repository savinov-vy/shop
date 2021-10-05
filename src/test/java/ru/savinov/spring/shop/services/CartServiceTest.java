package ru.savinov.spring.shop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.savinov.spring.shop.dto.ProductDTO;
import ru.savinov.spring.shop.entities.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        apple = new Product(1L, "apple", 10);
        orange = new Product(2L, "orange", 20);
        banana = new Product(3L, "banana", 30);
        productsDTO = new ArrayList<>(Arrays.asList(
                ProductDTO.of(apple, 1),
                ProductDTO.of(orange, 2),
                ProductDTO.of(banana, 3)
        ));
        when(productService.getProductById(1L)).thenReturn(apple);
        when(productService.getProductById(2L)).thenReturn(orange);
        when(productService.getProductById(3L)).thenReturn(banana);
        subject.addProductById(1L);
        subject.addProductById(2L);
        subject.addProductById(3L);

    }

    @Test
    void testGetSumPrice() {
        assertEquals(60, subject.getSumPrice());
    }

    @Test
    void testGetProductsDTO() {
        assertIterableEquals(productsDTO, subject.getProductsDTO());
    }

    @Test
    void testAddProductById() {
        Product butter = new Product(4L, "Butter", 50);
        when(productService.getProductById(4L)).thenReturn(butter);
        subject.addProductById(4L);
        assertThat(subject.getProductsDTO()
                .contains(ProductDTO.of(butter, 4)));
    }

    @Test
    void testRemoveProductByCount() {
        subject.removeProductByCount(1);
        assertThat(!subject.getProductsDTO().contains(apple));
        assertThat(subject.getProductsDTO().size() == 2);
    }
}