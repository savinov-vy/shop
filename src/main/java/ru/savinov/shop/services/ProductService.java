package ru.savinov.shop.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.savinov.shop.controllers.dto.ShopFilterDto;
import ru.savinov.shop.entities.Product;
import ru.savinov.shop.repositories.ProductRepository;
import ru.savinov.shop.repositories.specifications.CriteriaApi;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static ru.savinov.shop.common.ConstantProperties.DEFAULT_PAGE_NUMBER;
import static ru.savinov.shop.common.ConstantProperties.PAGE_SIZE;


@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> getProductsByFilter(int pageNumber, int pageSize, Specification<Product> productsSpecs) {
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
    public void updateProductById(Long idUpdate, String newTitle, Integer newPrice) {
        Product product = getProductById(idUpdate);
        product.setTitle(newTitle);
        product.setPrice(newPrice);
    }

    public List<Product> getProducts(ShopFilterDto productFilter) {
        Specification<Product> spec = Specification.where(null);
        applyPaginationFilter(productFilter);
        spec = applyTitleFilter(productFilter, spec);
        spec = applyPriceFilter(productFilter, spec);
        return getProductsByFilter(productFilter.getCurrentPage(), PAGE_SIZE, spec).getContent();
    }

    private void applyPaginationFilter(ShopFilterDto productFilter) {
        if (isNull(productFilter.getCurrentPage())) {
            productFilter.setCurrentPage(DEFAULT_PAGE_NUMBER);
        }
    }

    private Specification<Product> applyTitleFilter(ShopFilterDto productFilter, Specification<Product> spec) {
        if (nonNull(productFilter.getTitle())) {
            spec = spec.and(CriteriaApi.titleContains(productFilter.getTitle()));
        }
        return spec;
    }
    
    private Specification<Product> applyPriceFilter(ShopFilterDto productFilter, Specification<Product> spec) {
        if (nonNull(productFilter.getMinPrice())) {
            spec = spec.and(CriteriaApi.priceGreaterThanOrEq(productFilter.getMinPrice()));
        }
        if (nonNull(productFilter.getMaxPrice())) {
            spec = spec.and(CriteriaApi.priceLesserThanOrEq(productFilter.getMaxPrice()));
        }
        return spec;
    }

}


