package com.chstore.product.domain.repository;

import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(ProductId id);
    void deleteById(ProductId id);
}
