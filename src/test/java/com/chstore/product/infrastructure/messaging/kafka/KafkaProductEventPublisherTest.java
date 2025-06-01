package com.chstore.product.infrastructure.messaging.kafka;

import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.mother.ProductMother;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class KafkaProductEventPublisherTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private ObjectMapper objectMapper;

    private KafkaProductEventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventPublisher = new KafkaProductEventPublisher(kafkaTemplate, objectMapper);
    }

    @Test
    void publishProductCreated_shouldSendEventToKafka() throws JsonProcessingException {
        // Arrange
        Product product = ProductMother.random();
        String productJson = "{\"id\":\"" + product.getId().getValue() + "\"}";
        when(objectMapper.writeValueAsString(product)).thenReturn(productJson);

        CompletableFuture<SendResult<String, String>> future = new CompletableFuture<>();
        when(kafkaTemplate.send(eq("product.created"), anyString(), anyString())).thenReturn(future);
        future.complete(mock(SendResult.class));

        // Act
        eventPublisher.publishProductCreated(product);

        // Assert
        verify(objectMapper).writeValueAsString(product);
        verify(kafkaTemplate).send(eq("product.created"), eq(product.getId().toString()), eq(productJson));
    }

    @Test
    void publishProductUpdated_shouldSendEventToKafka() throws JsonProcessingException {
        // Arrange
        Product product = ProductMother.random();
        String productJson = "{\"id\":\"" + product.getId().getValue() + "\"}";
        when(objectMapper.writeValueAsString(product)).thenReturn(productJson);

        CompletableFuture<SendResult<String, String>> future = new CompletableFuture<>();
        when(kafkaTemplate.send(eq("product.updated"), anyString(), anyString())).thenReturn(future);
        future.complete(mock(SendResult.class));

        // Act
        eventPublisher.publishProductUpdated(product);

        // Assert
        verify(objectMapper).writeValueAsString(product);
        verify(kafkaTemplate).send(eq("product.updated"), eq(product.getId().toString()), eq(productJson));
    }

    @Test
    void publishProductDeleted_shouldSendEventToKafka() throws JsonProcessingException {
        // Arrange
        Product product = ProductMother.random();
        String productJson = "{\"id\":\"" + product.getId().getValue() + "\"}";
        when(objectMapper.writeValueAsString(product)).thenReturn(productJson);

        CompletableFuture<SendResult<String, String>> future = new CompletableFuture<>();
        when(kafkaTemplate.send(eq("product.deleted"), anyString(), anyString())).thenReturn(future);
        future.complete(mock(SendResult.class));

        // Act
        eventPublisher.publishProductDeleted(product);

        // Assert
        verify(objectMapper).writeValueAsString(product);
        verify(kafkaTemplate).send(eq("product.deleted"), eq(product.getId().toString()), eq(productJson));
    }

    @Test
    void publishEvent_shouldThrowRuntimeException_whenJsonProcessingFails() throws JsonProcessingException {
        // Arrange
        Product product = ProductMother.random();
        when(objectMapper.writeValueAsString(product)).thenThrow(new JsonProcessingException("Error serializing") {});

        // Act & Assert
        assertThrows(RuntimeException.class, () -> eventPublisher.publishProductCreated(product));
        verify(objectMapper).writeValueAsString(product);
        verify(kafkaTemplate, never()).send(anyString(), anyString(), anyString());
    }
}
