package ru.savinov.spring.study_shop.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkin")
public class CheckInController {
    private ProfileService profileService;
/** @PostMapping("/products/update")
public String shopPageUpdateTitle(@RequestParam Long idUpdate, @RequestParam String updateTitle, @RequestParam Integer updatePrice, Model model) {
productService.updateTitleById(idUpdate, updateTitle, updatePrice);
List<Product> allProducts = productService.getAllProducts();
model.addAttribute("products", allProducts);
return "shop";*/
    @Autowired
    public CheckInController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/form")
    public String checkIn() {
        return "checkIn";
    }

    @PostMapping("/form")
    public String checkInForm(@RequestParam String login, @RequestParam Integer password,
                              @RequestParam String name, @RequestParam String surname) {
        profileService.createNewProfile(login, password, name, surname);
        return "checkIn";
        }
}
 /**   @PostMapping("/form")
    public String checkInForm(@RequestBody ProfileRequest profileRequest) {
        System.out.println(profileRequest.getLogin());
        System.out.println(profileRequest.getName());
        System.out.println(profileRequest.getSurname());
        System.out.println(profileRequest.getPassword());
        // profileService.createNewProfile(profileRequest);
//        return "checkIn";*/