package ru.savinov.shop.test_helpers.factories;

import ru.savinov.shop.entities.Product;

import java.util.Arrays;
import java.util.List;

public class ProductFactory {

    public static List<Product> ofFullList() {
        return Arrays.asList(
                of(1L, "Milk", 10),
                of(2L, "Bread", 20),
                of(3L, "Butter", 30));
    }

    public static Product of(Long id, String title, Integer price) {
        return Product.builder()
                .id(id)
                .title(title)
                .price(price)
                .build();
    }

    public static Product ofOne() {
        return of(4L, "TestingProduct", 40);
    }
}
