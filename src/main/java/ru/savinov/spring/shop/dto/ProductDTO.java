package ru.savinov.spring.shop.dto;

import ru.savinov.spring.shop.entities.Product;

public class ProductDTO {
        private Long id;

        private String title;

        private Integer price;

        private Integer numberOfProduct;

        public ProductDTO(Product product, Integer numberOfProduct) {
            this.id = product.getId();
            this.title = product.getTitle();
            this.price = product.getPrice();
            this.numberOfProduct = numberOfProduct;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(Integer numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    @Override
        public String toString() {
            return "Product: " + this.id +" " +  this.title +" " +this.price;
        }
}
