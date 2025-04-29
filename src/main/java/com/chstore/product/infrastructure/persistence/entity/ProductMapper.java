package com.chstore.product.infrastructure.persistence.entity;
import com.chstore.product.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name.value", target = "name")
    @Mapping(source = "description.value", target = "description")
    @Mapping(source = "price.value", target = "price")
    @Mapping(source = "stock.value", target = "stock")
    ProductEntity toEntity(Product product);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name.value")
    @Mapping(source = "description", target = "description.value")
    @Mapping(source = "price", target = "price.value")
    @Mapping(source = "stock", target = "stock.value")
    Product toDomain(ProductEntity productEntity);
}
