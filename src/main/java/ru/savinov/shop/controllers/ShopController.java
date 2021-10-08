package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.savinov.shop.dto.ShopControllerDto;
import ru.savinov.shop.entities.Product;
import ru.savinov.shop.services.ProductService;

import static ru.savinov.shop.common.PageName.REDIRECT_SHOP_URL;
import static ru.savinov.shop.common.PageName.SHOP_PAGE;
import static ru.savinov.shop.common.Constant.INITIAL_PAGE;
import static ru.savinov.shop.controllers.constant.ShopControllerConstant.*;

@Controller
@AllArgsConstructor
public class ShopController {

    private ProductService productService;

    @GetMapping("/shop")
    public String shopPage(@ModelAttribute ShopControllerDto productFilter, Model model) {
        Page<Product> productsPages = productService.getProductByFilter(productFilter);
        model.addAttribute(PRODUCTS, productsPages.getContent());
        model.addAttribute(PAGE, INITIAL_PAGE);
        model.addAttribute(TOTAL_PAGE, productsPages.getTotalElements());
        model.addAttribute(MIN_PRICE, productFilter.getMinPrice());
        model.addAttribute(MAX_PRICE, productFilter.getMaxPrice());
        model.addAttribute(WORD, productFilter.getWord());
        return SHOP_PAGE;
    }

    @PostMapping("/products/add")
    public String shopPageAddProduct(@RequestParam String addProduct, @RequestParam Integer addPrice) {
        productService.addProduct(addProduct, addPrice);
        return REDIRECT_SHOP_URL;
    }

    @PostMapping("/products/update")
    public String shopPageUpdateTitle(@RequestParam Long idUpdate, @RequestParam String updateTitle, @RequestParam Integer updatePrice) {
        productService.updateTitleById(idUpdate, updateTitle, updatePrice);
        return REDIRECT_SHOP_URL;
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return REDIRECT_SHOP_URL;
    }

    @GetMapping("/data")
    @ResponseBody
    public String dataExample(@RequestParam(value = "serial", required = false) Long serial, @RequestParam("number") Long number) {
        return "S/N: " + serial + "/" + number;
    }
}
