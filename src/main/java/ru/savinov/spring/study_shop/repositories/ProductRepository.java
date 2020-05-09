package ru.savinov.spring.study_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savinov.spring.study_shop.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
