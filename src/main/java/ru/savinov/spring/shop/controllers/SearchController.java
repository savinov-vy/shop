package ru.savinov.spring.shop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.savinov.spring.shop.entities.AjaxResponseBody;
import ru.savinov.spring.shop.entities.Product;
import ru.savinov.spring.shop.entities.SearchCriteria;
import ru.savinov.spring.shop.entities.UserA;
import ru.savinov.spring.shop.services.UserServiceA;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    UserServiceA userService;

    @Autowired
    public void setUserService(UserServiceA userService) {
        this.userService = userService;
    }
    @ResponseBody
    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(
            @Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        List<UserA> users = userService.findByUserNameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);

    }
    @GetMapping("/test")
    public String shopPage(Model model) {

        return "ajax";
    }

}
