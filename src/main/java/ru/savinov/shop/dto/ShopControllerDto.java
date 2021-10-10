package ru.savinov.shop.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ShopControllerDto {
    Integer currentPage;
    String word;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}