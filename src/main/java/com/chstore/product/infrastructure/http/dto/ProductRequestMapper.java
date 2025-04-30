package com.chstore.product.infrastructure.http.dto;
import com.chstore.product.domain.exception.InvalidProductValueObjectException;
import com.chstore.product.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductRequestMapper {

    ProductRequestMapper INSTANCE = Mappers.getMapper(ProductRequestMapper.class);

    @Mapping(source = "id", target = "id.value")
    @Mapping(source = "name", target = "name.value")
    @Mapping(source = "description", target = "description.value")
    @Mapping(source = "price", target = "price.value")
    @Mapping(source = "stock", target = "stock.value")
    Product toDomain(ProductRequest request) throws InvalidProductValueObjectException;
}
