package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidProductIdException;
import lombok.Value;

import java.util.UUID;

@Value
public class ProductId {
    UUID value;

    public ProductId(UUID value) {
        if(value == null) {
            throw new InvalidProductIdException("Product ID cannot be null");
        }
        this.value = value;
    }
}
