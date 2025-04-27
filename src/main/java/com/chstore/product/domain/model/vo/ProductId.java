package com.chstore.product.domain.model;

import lombok.Value;

import java.util.UUID;

@Value
public class ProductId {
    UUID value;

    public ProductId(UUID value) {
        if(value == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        this.value = value;
    }
}
