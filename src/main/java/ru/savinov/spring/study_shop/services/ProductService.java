package ru.savinov.spring.study_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.savinov.spring.study_shop.entities.Product;
import ru.savinov.spring.study_shop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.getOne(id);
    }


    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void addProduct(String addTitle, Integer addPrice) {
        productRepository.save(new Product(addTitle, addPrice));

    }

    @Transactional
    public void updateTitleById(Long idUpdate, String newTitle, Integer newPrice) {
        productRepository.updateById(idUpdate, newTitle, newPrice);

    }



}


