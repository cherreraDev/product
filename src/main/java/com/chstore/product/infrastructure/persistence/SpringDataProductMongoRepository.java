package com.chstore.product.infrastructure.persistence;


import com.chstore.product.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataProductMongoRepository extends MongoRepository<ProductEntity, UUID> {
}
