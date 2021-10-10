package ru.savinov.shop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.savinov.shop.entities.Product;
import ru.savinov.shop.repositories.ProductRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    ProductService subject;

    @BeforeEach
    void setUp() {
        subject = new ProductService(productRepository);
    }

    @Test
    public void testAddProduct() {
        String productTitle = "Product";
        Integer productPrice = 10;
        subject.addProduct(productTitle, productPrice);
        verify(productRepository).save(new Product(productTitle, productPrice));
    }

}
