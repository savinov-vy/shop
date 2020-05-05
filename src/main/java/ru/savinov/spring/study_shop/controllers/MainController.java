package ru.savinov.spring.study_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.savinov.spring.study_shop.entities.Product;
import ru.savinov.spring.study_shop.services.ProductService;

@Controller
public class MainController {
    private ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")
    public String homePage() {
        return "index";
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {  //модель модель это ссылка на данные которые прокидываются в страницу HTML
        Product product = new Product(1L, "Milk", 80);
        model.addAttribute("prod", product); //добавили в модель атрибут и в этот атрибут добавили ссылку на объект
        return "shop";
    }

    @GetMapping("/details/{id}")
    public String detailsPage(Model model, @PathVariable("id") Long id) {  //Аннотация говорит, что где то в пути содержится id и спринг приводит его к Long
        Product selectedProduct = productService.getProduct().get(id.intValue() - 1);
        model.addAttribute("selectProduct", selectedProduct);

        return "details";
    }

}
