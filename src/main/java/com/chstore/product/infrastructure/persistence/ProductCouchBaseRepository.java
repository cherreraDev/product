package com.chstore.product.infrastructure.persistence;


import com.chstore.product.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCouchBaseRepository extends CouchbaseRepository<ProductEntity, UUID> {
}
