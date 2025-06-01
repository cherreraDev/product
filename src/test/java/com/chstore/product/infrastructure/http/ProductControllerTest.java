package com.chstore.product.infrastructure.http;

import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;
import com.chstore.product.domain.mother.ProductMother;
import com.chstore.product.domain.service.ProductService;
import com.chstore.product.infrastructure.http.dto.ProductRequest;
import com.chstore.product.infrastructure.http.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    void getProduct_shouldReturnProduct_whenProductExists() {
        // Arrange
        Product product = ProductMother.random();
        UUID productId = product.getId().getValue();
        when(productService.getById(any(ProductId.class))).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<Product> response = productController.getProduct(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService).getById(any(ProductId.class));
    }

    @Test
    void getProduct_shouldReturnNotFound_whenProductDoesNotExist() {
        // Arrange
        UUID productId = UUID.randomUUID();
        when(productService.getById(any(ProductId.class))).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.getProduct(productId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService).getById(any(ProductId.class));
    }

    @Test
    void createProduct_shouldReturnCreatedProduct() {
        // Arrange
        Product product = ProductMother.random();
        UUID id = product.getId().getValue();
        String name = product.getName().getValue();
        String description = product.getDescription().getValue();
        BigDecimal price = product.getPrice().getValue();
        int stock = product.getStock().getValue();

        ProductRequest request = new ProductRequest(id, name, description, price, stock);

        when(productService.save(any(Product.class))).thenReturn(product);

        // Act
        ResponseEntity<ProductResponse> response = productController.createProduct(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(productService).save(any(Product.class));
    }

    @Test
    void deleteProduct_shouldReturnNoContent_whenProductIsDeleted() {
        // Arrange
        UUID productId = UUID.randomUUID();
        doNothing().when(productService).deleteById(any(ProductId.class));

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(productId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService).deleteById(any(ProductId.class));
    }
}
