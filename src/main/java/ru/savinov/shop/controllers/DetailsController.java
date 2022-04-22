package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.savinov.shop.entities.Product;
import ru.savinov.shop.services.ProductService;

import static ru.savinov.shop.common.PageName.DETAILS_PAGE;
import static ru.savinov.shop.common.DetailsControllerConstant.*;

@Controller
@AllArgsConstructor
public class DetailsController {

    private final ProductService productService;

    @GetMapping("/details/{id}")
    public ModelAndView detailsPage(Model model, @PathVariable("id") Long id) {
        Product selectedProducts = productService.getProductById(id);
        model.addAttribute(SELECT_PRODUCT, selectedProducts);
        return new ModelAndView(DETAILS_PAGE);
    }
}
