package ru.savinov.spring.shop.services;

import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.repositories.ProductRepository;



import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    ProductService productService;
    Product testProduct;

    @BeforeEach
    public void init() {
        productService = new ProductService(productRepository);
        testProduct = new Product(1L, "Chiken", 99);

    }

    @Test
    void testGetProductById() {
        Product falsProduct = new Product(1L, "Chikenn", 99);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(testProduct));

        Product product = productService.getProductById(1L);
        Assert.assertNotNull(product);
        Assert.assertNotEquals(falsProduct, product);
        Assert.assertEquals(testProduct, product);

    }

}