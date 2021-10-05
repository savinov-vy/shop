package ru.savinov.spring.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.savinov.spring.shop.dto.ProductDTO;
import ru.savinov.spring.shop.entities.Product;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private List<ProductDTO> productsDTO;
    private ProductService productService;

    @Autowired
    public CartService(ProductService productService) {
        this.productService = productService;
        productsDTO = new ArrayList<>();
    }

    public int getSumPrice() {
        return productsDTO.stream()
                .mapToInt(product -> product.getPrice())
                .sum();
    }

    public List<ProductDTO> getProductsDTO() {
        return productsDTO;
    }

    @Secured(value = "USER")
    public void addProductById(Long id) {
        Product addProduct = productService.getProductById(id);
        productsDTO.add(ProductDTO.of(addProduct, getProductsDTO().size() + 1));
    }

    public void removeProductByCount(Integer idBuy) {
        productsDTO.remove((int)idBuy);
        refreshCartList();
    }

    private void refreshCartList() {
        List<ProductDTO> tempListProductDTO = new ArrayList<>();
        for (ProductDTO productDTO : productsDTO) {
            productDTO.setNumberOfProduct(tempListProductDTO.size() + 1);
            tempListProductDTO.add(productDTO);
        }
        productsDTO.clear();
        productsDTO.addAll(tempListProductDTO);
    }
}
