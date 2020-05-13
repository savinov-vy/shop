package ru.savinov.spring.study_shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.savinov.spring.study_shop.entities.Product;
import ru.savinov.spring.study_shop.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS) //для каждой сессии своя корзина


public class ShoppingCart {
    private List<Product> products;
    private ProductService productService;

   // @Autowired
    public ShoppingCart(List<Product> products) {
        this.products = products;
    }
    @Autowired
    public ShoppingCart(ProductService productService) {
        this.productService = productService;
    }

        public ShoppingCart() {
    }

    public List<Product> getProducts() {
        return products;
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
    }

    public void addProductById(Long id) {
        products.add(productService.getProductById(id));

    }



}
