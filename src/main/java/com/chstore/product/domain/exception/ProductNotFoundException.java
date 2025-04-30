package com.chstore.product.domain.exception;

import com.chstore.product.domain.model.vo.ProductId;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(ProductId id) {
        super("Product with id: " + id.getValue() + " not found");
    }
}
