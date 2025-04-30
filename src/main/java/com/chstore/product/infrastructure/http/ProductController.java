package com.chstore.product.infrastructure.http;

import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;
import com.chstore.product.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/product")
@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        ProductId productId = new ProductId(id);
        return service.getById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/new")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        Product product = ProductRequestMapper.INSTANCE.toDomain(request);
        Product saved = service.save(product);
        ProductResponse response = ProductResponseMapper.INSTANCE.toResponse(saved);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        ProductId productId = new ProductId(id);
        service.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}
