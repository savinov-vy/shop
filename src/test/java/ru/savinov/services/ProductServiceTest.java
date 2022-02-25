package ru.savinov.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.savinov.shop.entities.Product;
import ru.savinov.shop.repositories.ProductRepository;
import ru.savinov.shop.services.ProductService;
import ru.savinov.shop.test_helpers.factories.ProductFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.savinov.shop.test_helpers.TestConstant.NEW_PRODUCT_PRICE;
import static ru.savinov.shop.test_helpers.TestConstant.NEW_PRODUCT_TITLE;
import static ru.savinov.shop.test_helpers.TestConstant.PRODUCT_ID;
import static ru.savinov.shop.test_helpers.TestConstant.PRODUCT_PRICE;
import static ru.savinov.shop.test_helpers.TestConstant.PRODUCT_TITLE;

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
    void testAddProduct() {
        subject.addProduct(PRODUCT_TITLE, PRODUCT_PRICE);
        verify(productRepository).save(new Product(PRODUCT_TITLE, PRODUCT_PRICE));
    }

    @Test
    void testUpdateProductById_changePrice() {
        Optional<Product> optionalProduct = Optional.of(ProductFactory.ofProduct());
        Product product = optionalProduct.get();
        when(productRepository.findById(PRODUCT_ID)).thenReturn(optionalProduct);
        subject.updateProductById(PRODUCT_ID, NEW_PRODUCT_TITLE, NEW_PRODUCT_PRICE);
        assertEquals(product.getPrice(), NEW_PRODUCT_PRICE);
    }

    @Test
    void testUpdateProductById_changeTitle() {
        Optional<Product> optionalProduct = Optional.of(ProductFactory.ofProduct());
        Product product = optionalProduct.get();
        when(productRepository.findById(PRODUCT_ID)).thenReturn(optionalProduct);
        subject.updateProductById(PRODUCT_ID, NEW_PRODUCT_TITLE, NEW_PRODUCT_PRICE);
        assertEquals(product.getTitle(), NEW_PRODUCT_TITLE);
    }

}
