package ru.savinov.spring.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String shopPage(Model model,
                            @RequestParam(value = "word", required = false) String word,
                            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice
    ) {
        Specification<Product> specification = Specification.where(null);
        if (word != null) {
            specification = specification.and(ProductsSpecs.titleContains(word));
        }
        if (minPrice != null) {
            specification = specification.and(ProductsSpecs.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductsSpecs.priceLesserThanOrEq(maxPrice));
        }
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        model.addAttribute("word", word);
        return "shop";
    }

    @PostMapping("/products/add")
    public String shopPageAddProduct(@RequestParam String addProduct, @RequestParam Integer addPrice, Model model) {
        productService.addProduct(addProduct, addPrice);
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop";
    }

    @PostMapping("/products/update")
    public String shopPageUpdateTitle(@RequestParam Long idUpdate, @RequestParam String updateTitle, @RequestParam Integer updatePrice, Model model) {
        productService.updateTitleById(idUpdate, updateTitle, updatePrice);
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop";
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
