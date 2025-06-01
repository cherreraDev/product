package com.chstore.client.infrastructure.persistence;

import com.chstore.client.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for {@link ClientEntity}.
 * <p>
 * This interface automatically provides implementations for common CRUD operations
 * and can be extended with custom query methods if needed. The second generic type
 * {@code String} refers to the type of the ID field in {@code ClientEntity}.
 * </p>
 */
@Repository // Marks this as a Spring Data repository component
public interface SpringDataClientMongoRepository extends MongoRepository<ClientEntity, String> {

    // Spring Data MongoDB will automatically implement methods like:
    // - save(ClientEntity entity)
    // - findById(String id)
    // - findAll()
    // - deleteById(String id)
    // - etc.

    // Custom query methods can be defined here if needed, for example:
    // Optional<ClientEntity> findByEmail(String email);
}
