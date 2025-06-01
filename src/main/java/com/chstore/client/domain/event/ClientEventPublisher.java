package com.chstore.client.domain.event;

import com.chstore.client.domain.model.Client;
import com.chstore.client.domain.model.vo.ClientId;

/**
 * Interface for publishing client-related domain events.
 * Implementations of this interface will handle the actual event dissemination mechanism
 * (e.g., sending messages to a Kafka topic, using an in-memory event bus, etc.).
 */
public interface ClientEventPublisher {

    /**
     * Publishes an event when a new client is created.
     *
     * @param client The client that was created.
     */
    void publishClientCreatedEvent(Client client);

    /**
     * Publishes an event when a client's information is updated.
     *
     * @param client The client that was updated.
     */
    void publishClientUpdatedEvent(Client client);

    /**
     * Publishes an event when a client is deleted.
     *
     * @param clientId The ID of the client that was deleted.
     */
    void publishClientDeletedEvent(ClientId clientId);

    // Future events could include:
    // - publishClientEmailChangedEvent(ClientId clientId, Email newEmail);
    // - publishClientAddressChangedEvent(ClientId clientId, Address newAddress);
}
