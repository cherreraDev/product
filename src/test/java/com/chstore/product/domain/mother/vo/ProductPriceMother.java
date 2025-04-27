package com.chstore.product.domain.mother.vo;

import com.chstore.product.domain.model.vo.ProductPrice;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductPriceMother {
    public static ProductPrice random(){
        BigDecimal price = BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.HALF_UP);
        return new ProductPrice(price);
    }

    public static ProductPrice free(){
        return new ProductPrice(BigDecimal.ZERO);
    }
}
