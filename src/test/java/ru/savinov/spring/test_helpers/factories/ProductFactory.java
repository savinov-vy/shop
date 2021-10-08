package ru.savinov.spring.test_helpers.factories;

import ru.savinov.spring.shop.entities.Product;

import java.util.*;

public class ProductFactory {
    public static List<Product> ofProducts() {
        return java.util.Arrays.asList(
                new Product(1L, "Milk", 10),
                new Product(2L, "Bread", 20),
                new Product(3L, "Butter", 30));
    }

    public static Product ofProduct() {
        return  new Product(4L, "Product", 50);
    }
}
