package ru.savinov.spring.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.entities.User;
import ru.savinov.spring.shop.services.ProductService;

@RestController
public class ProductControllerA {
    @Autowired
    private ProductService productService;

@RequestMapping(method = RequestMethod.GET, value = "/a/get_product")
    public Product getTestProduct() {


    return productService.getProductById(1L);
}

}
