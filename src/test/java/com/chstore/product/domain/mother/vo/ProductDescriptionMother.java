package com.chstore.product.domain.mother.vo;

import com.chstore.product.domain.model.vo.ProductDescription;

import static com.chstore.product.faker.DataGenerator.randomString;

public class ProductDescriptionMother {
    public static ProductDescription random(int minLength, int maxLength){
        return new ProductDescription(randomString(minLength, maxLength));
    }
}
