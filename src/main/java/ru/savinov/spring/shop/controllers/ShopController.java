package ru.savinov.spring.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.repositories.specifications.ProductsSpecs;
import ru.savinov.spring.shop.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShopController {
    private ProductService productService;
    private static final int PAGE_SIZE = 10;
    private static final int INITIAL_PAGE = 0;

    @Autowired
    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String shopPage(Model model,
                           @RequestParam(value = "page", required = false) Integer currentPage,
                           @RequestParam(value = "word", required = false) String word,
                           @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                           @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice
    ) {

        if (currentPage == null) {
            currentPage = INITIAL_PAGE;
        }
        Specification<Product> spec = Specification.where(null);
        if (word != null) {
            spec = spec.and(ProductsSpecs.titleContains(word));
        }
        if (minPrice != null) {
            spec = spec.and(ProductsSpecs.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecs.priceLesserThanOrEq(maxPrice));
        }
        Page<Product> productsPages = productService.getProductsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        List<Product> allProducts = productsPages.getContent();

        model.addAttribute("products", productsPages.getContent());
        model.addAttribute("page", INITIAL_PAGE);
        model.addAttribute("totalPage", productsPages.getTotalElements());
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("word", word);
        model.addAttribute("products", allProducts);
        return "shop";
    }

    @PostMapping("/products/add")
    public String shopPageAddProduct(@RequestParam String addProduct, @RequestParam Integer addPrice) {
        productService.addProduct(addProduct, addPrice);
        return "redirect:/shop";
    }

    @PostMapping("/products/update")
    public String shopPageUpdateTitle(@RequestParam Long idUpdate, @RequestParam String updateTitle, @RequestParam Integer updatePrice) {
        productService.updateTitleById(idUpdate, updateTitle, updatePrice);
        return "redirect:/shop";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/shop";
    }

    @GetMapping("/data")
    @ResponseBody
    public String dataExample(@RequestParam(value = "serial", required = false) Long serial, @RequestParam("number") Long number) {  //equired = false - параметр не является обязательным
        return "S/N: " + serial + "/" + number;
    }
}
