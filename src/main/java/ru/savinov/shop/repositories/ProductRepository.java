package ru.savinov.shop.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.savinov.shop.entities.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Modifying
    @Query("update Product p set p.title = ?2, p.price = ?3 where p.id = ?1")
    void updateById( Long id, String title, Integer price);
}

