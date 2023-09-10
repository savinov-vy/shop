package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.savinov.shop.controllers.dto.BasketBuyDto;
import ru.savinov.shop.controllers.dto.ProductDTO;
import ru.savinov.shop.entities.User;
import ru.savinov.shop.services.CartService;
import ru.savinov.shop.services.KafkaProducerService;
import ru.savinov.shop.utils.security.SecurityUtils;

import java.util.List;

import static ru.savinov.shop.common.CartControllerConstant.TOTAL_PRICE;
import static ru.savinov.shop.common.PageName.CART_PAGE;
import static ru.savinov.shop.common.PageName.CART_PAGE_URL;
import static ru.savinov.shop.common.PageName.REDIRECT_CART_URL;
import static ru.savinov.shop.common.ShopControllerConstant.PRODUCTS;

@Slf4j
@Controller
@RequestMapping(CART_PAGE_URL)
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final SecurityUtils securityUtils;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/remove/{id}")
    public String removeByIdFromCart(@PathVariable("id") Long id) {
        cartService.removeProductById(id);
        return REDIRECT_CART_URL;
    }

    @GetMapping("")
    public String showCart(Model model) {
        User currentUser = securityUtils.getCurrentUser();
        log.info("Show card for user, Login = {}", currentUser.getLogin());
        model.addAttribute(PRODUCTS, ProductDTO.of(cartService.getProducts()));
        model.addAttribute(TOTAL_PRICE, CartService.getSumPrice(cartService.getProducts()));
        return CART_PAGE;
    }

    /**
     * to process orders, this project must be run through Docker
     */
    @PostMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> buyProduct(@RequestBody List<ProductDTO> productDtoList) {
        User currentUser = securityUtils.getCurrentUser();
        BasketBuyDto toHandle = BasketBuyDto.of(currentUser.getLogin(), productDtoList);
        boolean successfulSend = kafkaProducerService.send(toHandle);
        cartService.clearCart();
        if (successfulSend) {
            return new ResponseEntity<>("Basket successful sent", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Basket not sent", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
