package ru.savinov.spring.shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//для каждой сессии своя корзина
public class ShoppingCart {

    private static int countProduct = 0;
    private List<Product> products;
    private ProductService productService;
    private Product foundProduct;

    public static int getCountProduct() {
        return countProduct;
    }


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
        Product addProduct = productService.getProductById(id);
        products.add(addProduct);
        countProduct++;
    }

    public void removeProductByCount(Long idBuy) {
        products.remove(foundProduct(idBuy));
        countProduct--;
    }

    private Product foundProduct(Long idBuy) {

        for (Product product : products) {
            if (idBuy == product.getId()) foundProduct = product;
        }
        return foundProduct;
    }


}
