package com.chstore.product.domain.repository;

import com.chstore.product.domain.model.Client;
import com.chstore.product.domain.model.vo.ClientId;

import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(ClientId id);
    void deleteById(ClientId id);
}