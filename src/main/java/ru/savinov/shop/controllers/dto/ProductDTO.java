package ru.savinov.shop.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.savinov.shop.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    private String title;

    private Integer price;

    public static ProductDTO of(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .build();
    }

    public static List<ProductDTO> of(List<Product> products) {
        return products.stream()
                       .map(ProductDTO::of)
                       .collect(Collectors.toList());
    }
}
