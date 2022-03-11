package ru.savinov.shop.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.savinov.shop.entities.Product;

import java.math.BigDecimal;

public class CriteriaApi {
    public static Specification<Product> titleContains(String word) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%"+word+"%");
    }

    public static Specification<Product> priceGreaterThanOrEq(BigDecimal value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
    }
    public static Specification<Product> priceLesserThanOrEq(BigDecimal value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
    }
}
