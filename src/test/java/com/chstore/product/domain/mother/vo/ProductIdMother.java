package com.chstore.product.domain.mother.vo;

import com.chstore.product.domain.model.vo.ProductId;

import java.util.UUID;

public class ProductIdMother {
    public static ProductId random(){
        return new ProductId(UUID.randomUUID());
    }
}
