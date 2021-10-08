package ru.savinov.spring.shop.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.savinov.spring.shop.dto.ProductDTO;
import ru.savinov.spring.shop.entities.Product;

import java.util.*;

@Service
@Getter
@Setter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private List<ProductDTO> productsDTO;
    private ProductService productService;

    @Autowired
    public CartService(ProductService productService) {
        this.productService = productService;
        this.productsDTO = new ArrayList<>();
    }

    public int getSumPrice() {
        return productsDTO.stream()
                .mapToInt(product -> product.getPrice())
                .sum();
    }

    @Secured(value = "USER")
    public void addProductById(Long id) {
        Product addProduct = productService.getProductById(id);
        ProductDTO productDTO = ProductDTO.of(addProduct, getProductsDTO().size() + 1);
        ArrayList<ProductDTO> tmpList = new ArrayList<>(productsDTO);
        tmpList.add(productDTO);
        this.productsDTO = tmpList;
    }

    public void removeProductByNumberOnCart(Integer idBuy) {
        productsDTO.remove((int)idBuy);
        refreshCartList();
    }

    private void refreshCartList() {
        List<ProductDTO> tempListProductDTO = new ArrayList<>();
        for (ProductDTO productDTO : productsDTO) {
            productDTO.setNumberOnCart(tempListProductDTO.size() + 1);
            tempListProductDTO.add(productDTO);
        }
        productsDTO.clear();
        productsDTO.addAll(tempListProductDTO);
    }
}
