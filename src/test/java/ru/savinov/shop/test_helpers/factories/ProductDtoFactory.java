package ru.savinov.shop.test_helpers.factories;

import ru.savinov.shop.controllers.dto.ProductDTO;

import java.util.Arrays;
import java.util.List;

public class ProductDtoFactory {

   public static List<ProductDTO> ofProductsDto() {
      return Arrays.asList(
              new ProductDTO(1L, "Milk", 10, 1),
              new ProductDTO(2L, "Bread", 20, 2),
              new ProductDTO(3L, "Butter", 30, 3));
   }

   public static List<ProductDTO> ofUnsortedProductsDto() {
      return Arrays.asList(
              new ProductDTO(1L, "Milk", 10, 1),
              new ProductDTO(5L, "Bread", 20, 5),
              new ProductDTO(3L, "Butter", 30, 3));
   }
}
