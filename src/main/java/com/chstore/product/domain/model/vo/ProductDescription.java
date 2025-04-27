package com.chstore.product.domain.model.vo;

import lombok.Value;

@Value
public class ProductDescription {
    String value;

    public ProductDescription(String value) {
        this.value = (value == null) ? "" : value;
    }
}
