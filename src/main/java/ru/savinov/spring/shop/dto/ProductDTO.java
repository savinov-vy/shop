package ru.savinov.spring.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.savinov.spring.shop.entities.Product;

@Data
@AllArgsConstructor
public class ProductDTO {
        private Long id;

        private String title;

        private Integer price;

        private Integer numberOfProduct;


        public static ProductDTO of(Product product, Integer numberOfProduct) {
                return new ProductDTO(product.getId(), product.getTitle(), product.getPrice(), numberOfProduct);
        }
}
