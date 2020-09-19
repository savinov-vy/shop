package ru.savinov.spring.study_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.savinov.spring.study_shop.entities.Product;
import ru.savinov.spring.study_shop.services.ProductService;

@Controller
public class DetailsController {

    private ProductService productService;

    @Autowired
    public DetailsController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/details/{id}")
    public ModelAndView detailsPage(Model model, @PathVariable("id") Long id) {
        Product selectedProducts = productService.getProductById(id);
        model.addAttribute("selectProduct", selectedProducts);
        ModelAndView mav = new ModelAndView("details.html");
        return mav;
    }

}
