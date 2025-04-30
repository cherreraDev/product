package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidProductValueObjectException;
import lombok.Value;

@Value
public class ProductStock {
    int value;

    public ProductStock(int value) {
        if(value < 0) {
            throw new InvalidProductValueObjectException("Stock cannot be negative");
        }
        this.value = value;
    }
}
