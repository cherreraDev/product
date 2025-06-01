package com.chstore.product.infrastructure.messaging.kafka;

import com.chstore.product.domain.event.ProductEventPublisher;
import com.chstore.product.domain.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProductEventPublisher implements ProductEventPublisher {

    private static final String PRODUCT_CREATED_TOPIC = "product.created";
    private static final String PRODUCT_UPDATED_TOPIC = "product.updated";
    private static final String PRODUCT_DELETED_TOPIC = "product.deleted";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProductEventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishProductCreated(Product product) {
        sendEvent(PRODUCT_CREATED_TOPIC, product);
    }

    @Override
    public void publishProductUpdated(Product product) {
        sendEvent(PRODUCT_UPDATED_TOPIC, product);
    }

    @Override
    public void publishProductDeleted(Product product) {
        sendEvent(PRODUCT_DELETED_TOPIC, product);
    }

    private void sendEvent(String topic, Product product) {
        try {
            String payload = objectMapper.writeValueAsString(product);
            kafkaTemplate.send(topic, product.getId().toString(), payload)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            System.err.println("Error publicando evento en Kafka: " + ex.getMessage());
                        } else {
                            System.out.println("Evento publicado en Kafka topic: " + topic + ", key: " + product.getId());
                        }
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing product to JSON", e);
        }
    }
}
