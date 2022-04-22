package ru.savinov.shop.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.savinov.shop.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@AllArgsConstructor
public class CartService {

    private List<Product> products;
    private ProductService productService;

    @Secured(value = "USER")
    public void addProduct(Long id) {
        Product addProduct = productService.getProductById(id);
        products.add(addProduct);
    }

    public void removeProductById(Long id) {
        products.remove(productService.getProductById(id));
    }

    public void clearCart() {
        products = new ArrayList<>();
    }

    public static int getSumPrice(List<Product> products) {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }
}
