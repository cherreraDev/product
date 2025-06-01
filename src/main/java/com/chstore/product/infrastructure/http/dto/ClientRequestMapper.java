package com.chstore.product.infrastructure.http.dto;

import com.chstore.product.domain.exception.InvalidClientValueObjectException;
import com.chstore.product.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientRequestMapper {

    ClientRequestMapper INSTANCE = Mappers.getMapper(ClientRequestMapper.class);

    @Mapping(source = "id", target = "id.value")
    @Mapping(source = "name", target = "name.value")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "phone", target = "phone.value")
    @Mapping(source = "street", target = "address.street")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "state", target = "address.state")
    @Mapping(source = "zipCode", target = "address.zipCode")
    @Mapping(source = "country", target = "address.country")
    Client toDomain(ClientRequest request) throws InvalidClientValueObjectException;
}