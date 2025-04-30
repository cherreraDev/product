package com.chstore.product.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document(collection = "products")
@Getter
@AllArgsConstructor
public class ProductEntity {
    @Id
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}
