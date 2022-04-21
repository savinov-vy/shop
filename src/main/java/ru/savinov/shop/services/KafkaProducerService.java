package ru.savinov.shop.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaProducerService {
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage() {
        log.info("send message");
        this.kafkaTemplate.send("cart", "message  from kafka");
    }
}
