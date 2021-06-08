package ru.savinov.spring.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.savinov.spring.shop.services.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("products", cartService.getProductsDTO());
        model.addAttribute("totalPrice", cartService.getSumPrice());
        return "cart";
    }

    @GetMapping("/add/{Id}")
    public String addProductToCart(Model model, @PathVariable("Id") Long id) {
        cartService.addProductById(id);
        return "redirect:/shop";
    }

    @GetMapping("/remove/{IdBuy}")
    public String removeProductToCart(Model model, @PathVariable("IdBuy") Integer idBuy) {
        cartService.removeProductByCount(idBuy -1);
        return "redirect:/cart";
    }
}
