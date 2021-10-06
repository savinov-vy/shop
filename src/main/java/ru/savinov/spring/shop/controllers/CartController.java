package ru.savinov.spring.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.savinov.spring.shop.services.CartService;

import static ru.savinov.spring.shop.common.PageName.CART_PAGE;
import static ru.savinov.spring.shop.common.PageName.CART_PAGE_URL;
import static ru.savinov.spring.shop.common.PageName.REDIRECT_CART_URL;
import static ru.savinov.spring.shop.common.PageName.REDIRECT_SHOP_URL;
import static ru.savinov.spring.shop.controllers.constant.CartControllerConstant.*;

@Controller
@RequestMapping(CART_PAGE_URL)
@AllArgsConstructor
public class CartController {

    private CartService cartService;

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute(PRODUCTS, cartService.getProductsDTO());
        model.addAttribute(TOTAL_PRICE, cartService.getSumPrice());
        return CART_PAGE;
    }

    @GetMapping("/add/{Id}")
    public String addProductToCart(Model model, @PathVariable("Id") Long id) {
        cartService.addProductById(id);
        return REDIRECT_SHOP_URL;
    }

    @GetMapping("/remove/{IdBuy}")
    public String removeProductToCart(Model model, @PathVariable("IdBuy") Integer idBuy) {
        cartService.removeProductByCount(idBuy -1);
        return REDIRECT_CART_URL;
    }
}
