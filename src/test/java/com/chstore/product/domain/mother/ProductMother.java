package com.chstore.product.domain.mother;

import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.mother.vo.*;

public class ProductMother {
    public static Product random(){
        return Product.builder()
                .id(ProductIdMother.random())
                .price(ProductPriceMother.random())
                .stock(ProductStockMother.random())
                .name(ProductNameMother.random(3,30))
                .description(ProductDescriptionMother.random(1, 150))
                .build();
    }

    public static Product productWithLongName() {
        return Product.builder()
                .id(ProductIdMother.random())
                .price(ProductPriceMother.random())
                .stock(ProductStockMother.random())
                .name(ProductNameMother.random(15, 30))
                .description(ProductDescriptionMother.random(1, 100))
                .build();
    }

    public static Product productWithLongDescription() {
        return Product.builder()
                .id(ProductIdMother.random())
                .price(ProductPriceMother.random())
                .stock(ProductStockMother.random())
                .name(ProductNameMother.random(3, 15))
                .description(ProductDescriptionMother.random(100, 500))
                .build();
    }

    public static Product productOutOfStock() {
        return Product.builder()
                .id(ProductIdMother.random())
                .price(ProductPriceMother.random())
                .stock(ProductStockMother.outOfStock())
                .name(ProductNameMother.random(3, 15))
                .description(ProductDescriptionMother.random(1, 100))
                .build();
    }

    public static Product productWithShortName() {
        return Product.builder()
                .id(ProductIdMother.random())
                .price(ProductPriceMother.random())
                .stock(ProductStockMother.random())
                .name(ProductNameMother.random(1, 3))
                .description(ProductDescriptionMother.random(1, 100))
                .build();
    }

    public static Product productFree(){
        return Product.builder()
                .id(ProductIdMother.random())
                .price(ProductPriceMother.free())
                .stock(ProductStockMother.random())
                .name(ProductNameMother.random(3,30))
                .description(ProductDescriptionMother.random(1, 150))
                .build();
    }
}
