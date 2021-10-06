package ru.savinov.spring.shop.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.savinov.spring.shop.dto.ShopControllerDto;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.repositories.ProductRepository;
import ru.savinov.spring.shop.repositories.specifications.ProductsSpecs;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static ru.savinov.spring.shop.common_dictionary.Constant.INITIAL_PAGE;
import static ru.savinov.spring.shop.common_dictionary.Constant.PAGE_SIZE;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    @Transactional (readOnly = true)
    public Page<Product> getProductsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Product> productsSpecs) {
        return productRepository.findAll(productsSpecs, PageRequest.of(pageNumber, pageSize));
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    @Secured(value = "ROLE_ADMIN")
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    @Secured(value = "ROLE_ADMIN")
    public void addProduct(String addTitle, Integer addPrice) {
        productRepository.save(new Product(addTitle, addPrice));

    }

    @Transactional
    @Secured(value = "ROLE_ADMIN")
    public void updateTitleById(Long idUpdate, String newTitle, Integer newPrice) {
        Product product = getProductById(idUpdate);
        product.setTitle(newTitle);
        product.setPrice(newPrice);

    }

    public Page<Product> getProductByFilter(ShopControllerDto productFilter) {
        if (isNull(productFilter.getCurrentPage())) {
            productFilter.setCurrentPage(INITIAL_PAGE);
        }
        Specification<Product> spec = Specification.where(null);
        if (nonNull(productFilter.getWord())) {
            spec = spec.and(ProductsSpecs.titleContains(productFilter.getWord()));
        }
        if (nonNull(productFilter.getMinPrice())) {
            spec = spec.and(ProductsSpecs.priceGreaterThanOrEq(productFilter.getMinPrice()));
        }
        if (nonNull(productFilter.getMaxPrice())) {
            spec = spec.and(ProductsSpecs.priceLesserThanOrEq(productFilter.getMaxPrice()));
        }
        Page<Product> productsPages = getProductsWithPagingAndFiltering(productFilter.getCurrentPage(), PAGE_SIZE, spec);
        return productsPages;
    }
}


