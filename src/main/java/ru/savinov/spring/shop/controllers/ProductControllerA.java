package ru.savinov.spring.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.services.ProductService;

@RestController
public class ProductControllerA {
    @Autowired
    private ProductService productService;

    @GetMapping("/a1")
    public Product getTestProduct() {
    return (productService.getProductById(1L));
}

    @PostMapping("/add1")
    public void addTest(@RequestBody Product product) {

        productService.addProduct(product.getTitle(), product.getPrice());


        }

}
