package ru.savinov.shop.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.savinov.shop.dto.ProductDTO;
import ru.savinov.shop.entities.Product;

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

    public void removeProductByNumberOnCart(int numberOnCart) {
        ArrayList<ProductDTO> tmpList = new ArrayList<>(productsDTO);
        tmpList.remove(numberOnCart-1);
        reSortedNumberOnCart(tmpList);
        this.productsDTO = tmpList;
    }

    private void reSortedNumberOnCart(List<ProductDTO> productsDTO) {
        List<ProductDTO> tempListProductDTO = new ArrayList<>();
        for (ProductDTO productDTO : productsDTO) {
            productDTO.setNumberOnCart(tempListProductDTO.size() + 1);
            tempListProductDTO.add(productDTO);
        }
        productsDTO.clear();
        productsDTO.addAll(tempListProductDTO);
    }
}