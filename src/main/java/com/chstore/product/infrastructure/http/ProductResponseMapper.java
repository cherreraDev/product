package com.chstore.product.infrastructure.http;

import com.chstore.product.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductResponseMapper {

    ProductResponseMapper INSTANCE = Mappers.getMapper(ProductResponseMapper.class);

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "description.value", target = "description")
    @Mapping(source = "price.value", target = "price")
    @Mapping(source = "stock.value", target = "stock")
    ProductResponse toResponse(Product product);
}
