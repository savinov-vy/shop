package ru.savinov.spring.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.services.ProductService;

import static ru.savinov.spring.shop.common.PageName.DETAILS_PAGE;

@Controller
@AllArgsConstructor
public class DetailsController {

    private ProductService productService;

    private static final String SELECT_PRODUCT = "selectProduct";


    @GetMapping("/details/{id}")
    public ModelAndView detailsPage(Model model, @PathVariable("id") Long id) {
        Product selectedProducts = productService.getProductById(id);
        model.addAttribute(SELECT_PRODUCT, selectedProducts);
        ModelAndView mav = new ModelAndView(DETAILS_PAGE);
        return mav;
    }
}
