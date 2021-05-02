package ru.savinov.spring.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.savinov.spring.shop.ObjectForForm.Cat;

@Controller
public class FormController {
    @GetMapping("/addcat")
    public String showAddCatForm(Model model) {
        Cat cat = new Cat(1L, "Barsik", "White");
        model.addAttribute("cat", cat);
        return "catForm";
    }

    @PostMapping("addcat")
    public String showAddCatForm(@ModelAttribute(value = "cat") Cat cat) {
        System.out.println(cat.getId() + " "+cat.getName()+" "+ cat.getColor());
        return "redirect:/index";
    }
}
