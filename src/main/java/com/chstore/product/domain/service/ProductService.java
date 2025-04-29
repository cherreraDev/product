package com.chstore.product.domain.service;

import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;

import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    Optional<Product> getById(ProductId id);
    void deleteById(ProductId id);
}
