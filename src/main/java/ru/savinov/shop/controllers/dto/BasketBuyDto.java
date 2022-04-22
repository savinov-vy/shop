package ru.savinov.shop.controllers.dto;

import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor(staticName = "of")
public class BasketBuyDto {
    private String buyerLogin;
    private List<ProductDTO> buyProducts;
}
