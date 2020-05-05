package ru.savinov.spring.study_shop.services;

import org.springframework.stereotype.Service;
import ru.savinov.spring.study_shop.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> product;
    public List<Product> getProduct() {
        return product;
    }

    @PostConstruct
    public void init() {
        product = new ArrayList<>();
        product.add(new Product(1L, "Milk", 80));
        product.add(new Product(2L, "Cheese", 320));
        product.add(new Product(3L, "Ball", 200));

    }


}
