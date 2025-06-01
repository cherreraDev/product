package com.chstore.client.infrastructure.messaging.kafka;

import com.chstore.client.domain.event.ClientEventPublisher;
import com.chstore.client.domain.model.Client;
import com.chstore.client.domain.model.vo.ClientId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaClientEventPublisher implements ClientEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(KafkaClientEventPublisher.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    // Kafka Topic Names - configurable via application.properties
    @Value("${kafka.topic.client.created:client-created}")
    private String clientCreatedTopic;

    @Value("${kafka.topic.client.updated:client-updated}")
    private String clientUpdatedTopic;

    @Value("${kafka.topic.client.deleted:client-deleted}")
    private String clientDeletedTopic;

    public KafkaClientEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        if (kafkaTemplate == null) {
            throw new IllegalArgumentException("KafkaTemplate cannot be null");
        }
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishClientCreatedEvent(Client client) {
        if (client == null) {
            log.warn("Attempted to publish a null client created event.");
            return;
        }
        log.info("Publishing ClientCreatedEvent for client ID: {} to topic: {}", client.getId().getValue(), clientCreatedTopic);
        try {
            // Spring Kafka with Jackson JSON serializer will handle Client object serialization.
            // The key for the Kafka message will be the client's ID.
            kafkaTemplate.send(clientCreatedTopic, client.getId().getValue(), client);
            log.debug("Successfully published ClientCreatedEvent for client ID: {}", client.getId().getValue());
        } catch (Exception e) {
            log.error("Error publishing ClientCreatedEvent for client ID: {}: {}", client.getId().getValue(), e.getMessage(), e);
            // Depending on requirements, could re-throw, or handle (e.g., dead-letter queue)
        }
    }

    @Override
    public void publishClientUpdatedEvent(Client client) {
        if (client == null) {
            log.warn("Attempted to publish a null client updated event.");
            return;
        }
        log.info("Publishing ClientUpdatedEvent for client ID: {} to topic: {}", client.getId().getValue(), clientUpdatedTopic);
        try {
            kafkaTemplate.send(clientUpdatedTopic, client.getId().getValue(), client);
            log.debug("Successfully published ClientUpdatedEvent for client ID: {}", client.getId().getValue());
        } catch (Exception e) {
            log.error("Error publishing ClientUpdatedEvent for client ID: {}: {}", client.getId().getValue(), e.getMessage(), e);
        }
    }

    @Override
    public void publishClientDeletedEvent(ClientId clientId) {
        if (clientId == null) {
            log.warn("Attempted to publish a client deleted event with null ID.");
            return;
        }
        log.info("Publishing ClientDeletedEvent for client ID: {} to topic: {}", clientId.getValue(), clientDeletedTopic);
        try {
            // For delete events, often just the ID is sufficient as payload.
            kafkaTemplate.send(clientDeletedTopic, clientId.getValue(), clientId.getValue());
            log.debug("Successfully published ClientDeletedEvent for client ID: {}", clientId.getValue());
        } catch (Exception e) {
            log.error("Error publishing ClientDeletedEvent for client ID: {}: {}", clientId.getValue(), e.getMessage(), e);
        }
    }
}
