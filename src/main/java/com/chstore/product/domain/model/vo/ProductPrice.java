package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidProductValueObjectException;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductPrice {
    BigDecimal value;

    public ProductPrice(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidProductValueObjectException("Price must be non-negative");
        }
        this.value = value;
    }
}
