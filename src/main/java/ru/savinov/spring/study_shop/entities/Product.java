package ru.savinov.spring.study_shop.entities;

import javax.persistence.*;

@Entity
@Table(name = "products") //таблица в базе данных называется products
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //запрашиваем генерацию Id у базы
    private Long id;

    @Column(name = "title")
    private String title; //название

    @Column(name = "price")
    private int price;

    public Product() {
    }

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product: [id=%id, title = %s, price = %d]", id, title, price);
    }




}
