package com.chstore.client.infrastructure.persistence;

import com.chstore.client.domain.model.Client;
import com.chstore.client.domain.model.vo.ClientId;
import com.chstore.client.domain.repository.ClientRepository;
import com.chstore.client.infrastructure.persistence.entity.ClientEntity;
import org.springframework.stereotype.Component; // Or @Repository

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component // Using @Component, but @Repository would also be appropriate
public class MongoClientRepository implements ClientRepository {

    private final SpringDataClientMongoRepository springDataMongoRepository;

    // Constructor injection
    public MongoClientRepository(SpringDataClientMongoRepository springDataMongoRepository) {
        if (springDataMongoRepository == null) {
            throw new IllegalArgumentException("SpringDataClientMongoRepository cannot be null");
        }
        this.springDataMongoRepository = springDataMongoRepository;
    }

    @Override
    public void save(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client to save cannot be null");
        }
        ClientEntity entity = ClientEntity.fromDomain(client);
        springDataMongoRepository.save(entity);
    }

    @Override
    public Optional<Client> findById(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("ClientId cannot be null");
        }
        Optional<ClientEntity> entityOptional = springDataMongoRepository.findById(clientId.getValue());
        return entityOptional.map(ClientEntity::toDomain);
    }

    @Override
    public void delete(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("ClientId for deletion cannot be null");
        }
        // Optional: Could check if client exists before deletion to throw a custom domain exception
        // if (!springDataMongoRepository.existsById(clientId.getValue())) {
        //     throw new ClientNotFoundException("Cannot delete. Client not found with ID: " + clientId.getValue());
        // }
        springDataMongoRepository.deleteById(clientId.getValue());
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> entities = springDataMongoRepository.findAll();
        return entities.stream()
                .map(ClientEntity::toDomain)
                .collect(Collectors.toList());
    }
}
