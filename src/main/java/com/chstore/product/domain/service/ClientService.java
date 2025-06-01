package com.chstore.product.domain.service;

import com.chstore.product.domain.model.Client;
import com.chstore.product.domain.model.vo.ClientId;

import java.util.Optional;

public interface ClientService {
    Client save(Client client);
    Optional<Client> getById(ClientId id);
    void deleteById(ClientId id);
}