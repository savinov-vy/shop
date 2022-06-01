package ru.savinov.shop.services;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.savinov.shop.controllers.dto.BasketBuyDto;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;
    private Gson gson;

    public boolean send(BasketBuyDto toHandle) {
        try {
            String basketToHandle = gson.toJson(toHandle);
            kafkaTemplate.send("basket", basketToHandle);
            log.info("Send basket products to handle, basket to handle: {}", basketToHandle);
            return true;
        } catch (Exception e) {
            log.info("Basket not sent, with error: {}", e.getMessage());
            return false;
        }
    }
}
