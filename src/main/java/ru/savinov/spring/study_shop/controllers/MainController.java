package ru.savinov.spring.study_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.study_shop.entities.Product;
import ru.savinov.spring.study_shop.services.ProductService;
import java.util.List;

@Controller
public class MainController {
    private ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/shop")
    public String shopPage(Model model) {  //модель модель это ссылка на данные которые прокидываются в страницу HTML
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop";
    }

    @PostMapping("/products/add")
    public String shopPageAddProduct(@RequestParam String addProduct, @RequestParam Integer addPrice, Model model) {  //модель модель это ссылка на данные которые прокидываются в страницу HTML
        productService.addProduct(addProduct, addPrice);
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop";
    }

    @PostMapping("/products/update")
    public String shopPageUpdateTitle(@RequestParam Long idUpdate, @RequestParam String updateTitle, @RequestParam Integer updatePrice, Model model) {
        productService.updateTitleById(idUpdate, updateTitle, updatePrice);
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop";
    }


    @GetMapping("/products/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/shop"; // перенаправляем по адресу shop/
    }

    // вытащить несколько параметров из запроса
    @GetMapping("/data")
    @ResponseBody // ответом на запрос будет обычная строка
    public String dataExample(@RequestParam(value = "serial", required = false) Long serial, @RequestParam("number") Long number) {  //http://localhost:8189/app/data?serial=10&number=100 (value = "serial", required = falseобозначает что параметр не является обязательным)
        return "S/N: " + serial + "/" + number;
    }

}
