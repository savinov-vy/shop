package ru.savinov.shop.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(staticName = "of")
public class ShopFilterDto {
    Integer currentPage;
    String title;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}
