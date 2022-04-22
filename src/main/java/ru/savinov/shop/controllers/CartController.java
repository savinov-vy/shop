package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ru.savinov.shop.common.CartControllerConstant.STATUS_OK;
import static ru.savinov.shop.common.CartControllerConstant.TOTAL_PRICE;
import static ru.savinov.shop.common.PageName.CART_PAGE;
import static ru.savinov.shop.common.PageName.CART_PAGE_URL;
import static ru.savinov.shop.common.PageName.REDIRECT_CART_URL;
import static ru.savinov.shop.common.PageName.REDIRECT_SHOP_URL;
import static ru.savinov.shop.common.ShopControllerConstant.PRODUCTS;

@Slf4j
@Controller
@RequestMapping(CART_PAGE_URL)
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final SecurityUtils securityUtils;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("")
    public String showCart(Model model) {
        User currentUser = securityUtils.getCurrentUser();
        log.info("Show card for user, Login = {}", currentUser.getLogin());
        model.addAttribute(PRODUCTS, ProductDTO.of(cartService.getProducts()));
        model.addAttribute(TOTAL_PRICE, CartService.getSumPrice(cartService.getProducts()));
        return CART_PAGE;
    }

    @GetMapping("/add/{Id}")
    public String addProductToCart(@PathVariable("Id") Long id) {
        cartService.addProduct(id);
        return REDIRECT_SHOP_URL;
    }

    @GetMapping("/remove/{id}")
    public String removeByIdFromCart(@PathVariable("id") Long id) {
        cartService.removeProductById(id);
        return REDIRECT_CART_URL;
    }

    @PostMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public void buyProduct(@RequestBody List<ProductDTO> productDtoList, HttpServletResponse httpServletResponse) {
        User currentUser = securityUtils.getCurrentUser();
        BasketBuyDto toHandle = BasketBuyDto.of(currentUser.getLogin(), productDtoList);
        kafkaProducerService.send(toHandle);
        cartService.clearCart();
        httpServletResponse.setStatus(STATUS_OK);
    }
}
