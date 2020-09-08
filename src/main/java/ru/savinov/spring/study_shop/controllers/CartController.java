package ru.savinov.spring.study_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.savinov.spring.study_shop.utils.ShoppingCart;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ShoppingCart shoppingCart;

    @Autowired
    public CartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public CartController() {
    }

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("products", shoppingCart.getProducts());
        model.addAttribute("count", ShoppingCart.getCountProduct());
        return "cart";
    }

    @GetMapping("/add/{Id}")
    public String addProductToCart(Model model, @PathVariable("Id") Long id) {
        shoppingCart.addProductById(id);
        return "redirect:/shop";
    }

    @GetMapping("/remove/{IdBuy}")
    public String removeProductToCart(Model model, @PathVariable("IdBuy") Long idBuy) {
        model.addAttribute("sumProd", ShoppingCart.getCountProduct());
        shoppingCart.removeProductByCount(idBuy);
        return "cart";
    }
}
