package com.chstore.product.domain.model;

import lombok.Value;

@Value
public class ProductStock {
    int value;

    public ProductStock(int value) {
        if(value < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.value = value;
    }
}
