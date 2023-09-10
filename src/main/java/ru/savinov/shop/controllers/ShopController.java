package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.savinov.shop.controllers.dto.ShopFilterDto;
import ru.savinov.shop.entities.Product;
import ru.savinov.shop.services.CartService;
import ru.savinov.shop.services.ProductService;

import java.util.List;

import static ru.savinov.shop.common.PageName.REDIRECT_SHOP_URL;
import static ru.savinov.shop.common.PageName.SHOP_PAGE;
import static ru.savinov.shop.common.ConstantProperties.DEFAULT_PAGE_NUMBER;
import static ru.savinov.shop.common.ShopControllerConstant.*;

@Controller
@AllArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/showcase")
    public String shopPage(@ModelAttribute ShopFilterDto productFilter, Model model) {
        List<Product> products = productService.getProducts(productFilter);
        model.addAttribute(PAGE, DEFAULT_PAGE_NUMBER);
        model.addAttribute(PRODUCT_FILTER, productFilter);
        model.addAttribute(PRODUCTS, products);
        model.addAttribute(TOTAL_PAGE, products.size());
        return SHOP_PAGE;
    }

    @PostMapping("/products/add")
    public String shopPageAddProduct(@RequestParam String addProduct, @RequestParam Integer addPrice) {
        productService.addProduct(addProduct, addPrice);
        return REDIRECT_SHOP_URL;
    }

    @GetMapping("/my-basket/add/{id}")
    public String shopPageAddProduct(@PathVariable("id") Long id) {
        cartService.addProduct(id);
        return REDIRECT_SHOP_URL;
    }

    @PostMapping("/products/update")
    public String shopPageUpdateTitle(@RequestParam Long idUpdate, @RequestParam String updateTitle, @RequestParam Integer updatePrice) {
        productService.updateProductById(idUpdate, updateTitle, updatePrice);
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
