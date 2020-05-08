package ru.savinov.spring.study_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savinov.spring.study_shop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { // в параметрах указываем сущность с которой необходимо работать и тип Id
}
