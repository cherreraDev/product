package com.chstore.product.infrastructure.messaging.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductKafkaListener {

    @KafkaListener(topics = "product.created", groupId = "product-service-group")
    public void listen(String message) {
        System.out.println("Mensaje recibido: " + message);
    }
}