package ru.savinov.spring.shop.dto;

import ru.savinov.spring.shop.entities.Product;

import java.util.Objects;

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
        return "ProductDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", numberOfProduct=" + numberOfProduct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getNumberOfProduct(), that.getNumberOfProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getPrice(), getNumberOfProduct());
    }
}
