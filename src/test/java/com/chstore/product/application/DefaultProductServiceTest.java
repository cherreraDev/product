package com.chstore.product.application;

import com.chstore.product.domain.event.ProductEventPublisher;
import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;
import com.chstore.product.domain.mother.ProductMother;
import com.chstore.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductEventPublisher eventPublisher;

    private DefaultProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new DefaultProductService(productRepository, eventPublisher);
    }

    @Test
    void save_shouldSaveProductAndPublishCreatedEvent_whenProductDoesNotExist() {
        // Arrange
        Product product = ProductMother.random();
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product result = productService.save(product);

        // Assert
        assertEquals(product, result);
        verify(productRepository).save(product);
        verify(eventPublisher).publishProductCreated(product);
        verify(eventPublisher, never()).publishProductUpdated(any());
    }

    @Test
    void save_shouldSaveProductAndPublishUpdatedEvent_whenProductExists() {
        // Arrange
        Product product = ProductMother.random();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product result = productService.save(product);

        // Assert
        assertEquals(product, result);
        verify(productRepository).save(product);
        verify(eventPublisher).publishProductUpdated(product);
        verify(eventPublisher, never()).publishProductCreated(any());
    }

    @Test
    void getById_shouldReturnProduct_whenProductExists() {
        // Arrange
        Product product = ProductMother.random();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.getById(product.getId());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
        verify(productRepository).findById(product.getId());
    }

    @Test
    void getById_shouldReturnEmpty_whenProductDoesNotExist() {
        // Arrange
        ProductId id = new ProductId(UUID.randomUUID());
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Product> result = productService.getById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(productRepository).findById(id);
    }

    @Test
    void deleteById_shouldDeleteProductAndPublishEvent_whenProductExists() {
        // Arrange
        Product product = ProductMother.random();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // Act
        productService.deleteById(product.getId());

        // Assert
        verify(productRepository).deleteById(product.getId());
        verify(eventPublisher).publishProductDeleted(product);
    }

    @Test
    void deleteById_shouldNotDeleteOrPublish_whenProductDoesNotExist() {
        // Arrange
        ProductId id = new ProductId(UUID.randomUUID());
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        productService.deleteById(id);

        // Assert
        verify(productRepository, never()).deleteById(any());
        verify(eventPublisher, never()).publishProductDeleted(any());
    }
}