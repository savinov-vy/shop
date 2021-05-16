package ru.savinov.spring.shop.services;

import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    ProductService productService;
    Product testProduct;
    List<Product> testListProducts;

    @BeforeEach
    public void init() {
        productService = new ProductService(productRepository);
        testProduct = new Product(1L, "Chiken", 99);

        testListProducts = new ArrayList<>();
        testListProducts.add(testProduct);
        testListProducts.add(new Product(2L, "Milk", 88));
        testListProducts.add(new Product(3L, "Fish", 77));
    }

//    @Test
//    void testGetAllProduct() {
//        List<Product> falseList = new ArrayList<>();
//        falseList.addAll(testListProducts);
//        falseList.set(2, new Product(3L, "Finish", 77));
//        System.out.println(falseList);
//
//        when(productRepository.findAll()).thenReturn(testListProducts);

//        List<Product> acceptList = productService.getAllProducts();

//        Assert.assertNotEquals(falseList, acceptList);
//        Assert.assertEquals(testListProducts, acceptList);
//    }

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