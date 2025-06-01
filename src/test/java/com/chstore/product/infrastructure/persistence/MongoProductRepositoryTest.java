package com.chstore.product.infrastructure.persistence;

import com.chstore.product.domain.exception.ProductDeleteException;
import com.chstore.product.domain.exception.ProductNotFoundException;
import com.chstore.product.domain.exception.ProductSaveException;
import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;
import com.chstore.product.domain.mother.ProductMother;
import com.chstore.product.infrastructure.persistence.entity.ProductEntity;
import com.chstore.product.infrastructure.persistence.entity.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MongoProductRepositoryTest {

    @Mock
    private SpringDataProductMongoRepository mongoRepository;

    private MongoProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productRepository = new MongoProductRepository(mongoRepository);
    }

    @Test
    void save_shouldReturnSavedProduct_whenSaveIsSuccessful() {
        // Arrange
        Product product = ProductMother.random();
        ProductEntity productEntity = ProductMapper.INSTANCE.toEntity(product);
        
        when(mongoRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        // Act
        Product savedProduct = productRepository.save(product);

        // Assert
        assertNotNull(savedProduct);
        assertEquals(product.getId().getValue(), savedProduct.getId().getValue());
        assertEquals(product.getName().getValue(), savedProduct.getName().getValue());
        assertEquals(product.getDescription().getValue(), savedProduct.getDescription().getValue());
        assertEquals(product.getPrice().getValue(), savedProduct.getPrice().getValue());
        assertEquals(product.getStock().getValue(), savedProduct.getStock().getValue());
        
        verify(mongoRepository).save(any(ProductEntity.class));
    }

    @Test
    void save_shouldThrowProductSaveException_whenSaveFails() {
        // Arrange
        Product product = ProductMother.random();
        when(mongoRepository.save(any(ProductEntity.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(ProductSaveException.class, () -> productRepository.save(product));
        verify(mongoRepository).save(any(ProductEntity.class));
    }

    @Test
    void findById_shouldReturnProduct_whenProductExists() {
        // Arrange
        Product product = ProductMother.random();
        ProductEntity productEntity = ProductMapper.INSTANCE.toEntity(product);
        ProductId productId = product.getId();
        
        when(mongoRepository.findById(productId.getValue())).thenReturn(Optional.of(productEntity));

        // Act
        Optional<Product> foundProduct = productRepository.findById(productId);

        // Assert
        assertTrue(foundProduct.isPresent());
        assertEquals(product.getId().getValue(), foundProduct.get().getId().getValue());
        assertEquals(product.getName().getValue(), foundProduct.get().getName().getValue());
        assertEquals(product.getDescription().getValue(), foundProduct.get().getDescription().getValue());
        assertEquals(product.getPrice().getValue(), foundProduct.get().getPrice().getValue());
        assertEquals(product.getStock().getValue(), foundProduct.get().getStock().getValue());
        
        verify(mongoRepository).findById(productId.getValue());
    }

    @Test
    void findById_shouldReturnEmpty_whenProductDoesNotExist() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        when(mongoRepository.findById(productId.getValue())).thenReturn(Optional.empty());

        // Act
        Optional<Product> foundProduct = productRepository.findById(productId);

        // Assert
        assertFalse(foundProduct.isPresent());
        verify(mongoRepository).findById(productId.getValue());
    }

    @Test
    void findById_shouldThrowProductNotFoundException_whenFindByIdFails() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        when(mongoRepository.findById(productId.getValue())).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> productRepository.findById(productId));
        verify(mongoRepository).findById(productId.getValue());
    }

    @Test
    void deleteById_shouldDeleteProduct_whenDeleteIsSuccessful() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        doNothing().when(mongoRepository).deleteById(productId.getValue());

        // Act
        productRepository.deleteById(productId);

        // Assert
        verify(mongoRepository).deleteById(productId.getValue());
    }

    @Test
    void deleteById_shouldThrowProductDeleteException_whenDeleteFails() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        doThrow(new RuntimeException("Database error")).when(mongoRepository).deleteById(productId.getValue());

        // Act & Assert
        assertThrows(ProductDeleteException.class, () -> productRepository.deleteById(productId));
        verify(mongoRepository).deleteById(productId.getValue());
    }
}