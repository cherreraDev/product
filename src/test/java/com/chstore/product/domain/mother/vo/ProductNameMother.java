package com.chstore.product.domain.mother.vo;

import com.chstore.product.domain.model.vo.ProductName;

import static com.chstore.product.faker.DataGenerator.randomString;

public class ProductNameMother {
    public static ProductName random(int minLength, int maxLength){
        return new ProductName(randomString(minLength, maxLength));
    }
}
