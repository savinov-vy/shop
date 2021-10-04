package ru.savinov.spring.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.savinov.spring.shop.ObjectForForm.Cat;
import ru.savinov.spring.shop.entities.Product;

import java.util.Arrays;

@Controller
public class NewShopController {
    @GetMapping("/newShop")
    public String showAddCatForm(Model model) {
        model.addAttribute("products", Arrays.asList(new Product(1L,"Product", 20),
                                                        new Product(1L,"Product2", 20)));
        return "newShop";
    }
}
