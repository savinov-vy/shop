package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.savinov.shop.services.KafkaProducerService;

import static ru.savinov.shop.common.PageName.INDEX_PAGE;

@Controller
@AllArgsConstructor
public class IndexController {
private KafkaProducerService kafkaProducerService;

    @GetMapping("/home")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView(INDEX_PAGE);
        return mav;
    }

    @GetMapping("/kafka")
    public ModelAndView kafkaPage() {
        ModelAndView mav = new ModelAndView(INDEX_PAGE);
        kafkaProducerService.sendMessage();
        return mav;
    }
}
