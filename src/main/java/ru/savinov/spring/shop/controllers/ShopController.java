package ru.savinov.spring.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.shop.dto.ShopControllerDto;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.services.ProductService;

import java.util.List;

import static ru.savinov.spring.shop.common_dictionary.PageName.SHOP_PAGE;
import static ru.savinov.spring.shop.common_dictionary.Constant.INITIAL_PAGE;

@Controller
public class ShopController {
    private ProductService productService;


    @Autowired
    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String shopPage(@ModelAttribute ShopControllerDto productFilter, Model model) {
        Page<Product> productsPages = productService.getProductByFilter(productFilter);
        List<Product> allProducts = productsPages.getContent();
        model.addAttribute("products", productsPages.getContent());
        model.addAttribute("page", INITIAL_PAGE);
        model.addAttribute("totalPage", productsPages.getTotalElements());
        model.addAttribute("minPrice", productFilter.getMinPrice());
        model.addAttribute("maxPrice", productFilter.getMaxPrice());
        model.addAttribute("word", productFilter.getWord());
        model.addAttribute("products", allProducts);
        return SHOP_PAGE;
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
