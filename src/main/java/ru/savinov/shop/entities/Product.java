package ru.savinov.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    public Product(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: " + this.id + " " + this.title + " " + this.price;
    }

}
