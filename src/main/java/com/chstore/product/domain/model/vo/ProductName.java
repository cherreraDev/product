package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidProductValueObjectException;
import lombok.Value;

@Value
public class ProductName {
    String value;

    public ProductName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidProductValueObjectException("Product name cannot be blank");
        }
        this.value = value.trim();
    }
}
