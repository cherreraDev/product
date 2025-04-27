package com.chstore.product.domain.mother.vo;

import com.chstore.product.domain.model.vo.ProductStock;

public class ProductStockMother {
    public static ProductStock random(){
        return new ProductStock((int) (Math.random() * 100));
    }

    public static ProductStock outOfStock(){
        return new ProductStock(0);
    }
}
