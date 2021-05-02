package ru.savinov.spring.shop.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.savinov.spring.shop.entities.Product;

import java.math.BigDecimal;
// прописываются отдельные фильтры
// для применения фильтров repository должен наследоваться от JpaSpecificationExecutor
// root это ссылка на продукт
// criteriaBuilder - позволяет формировать критерии отбора
// root get title продукт запрашиваем title и он должен быть like "%"+word+"%"
public class ProductsSpecs {
    public static Specification<Product> titleContains(String word) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%"+word+"%");
    }                                                                                               // where title like %word%

    public static Specification<Product> priceGreaterThanOrEq(BigDecimal value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
        };
    }
    public static Specification<Product> priceLesserThanOrEq(BigDecimal value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
        };
    }
}
