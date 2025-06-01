package com.chstore.product.domain.event;

import com.chstore.product.domain.model.Client;

public interface ClientEventPublisher {
    void publishClientCreated(Client client);
    void publishClientUpdated(Client client);
    void publishClientDeleted(Client client);
}