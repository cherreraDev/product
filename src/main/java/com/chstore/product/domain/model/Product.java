package com.chstore.product.domain.model;

import com.chstore.product.domain.model.vo.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {
    private final ProductId id;
    private final ProductName name;
    private final ProductDescription description;
    private final ProductPrice price;
    private ProductStock stock;

    public void updateStock(ProductStock newStock) {
        if(newStock == null || newStock.getValue() < 0) {
            throw new IllegalArgumentException("stock cannot be negative");
        }
        this.stock = newStock;
    }
}
