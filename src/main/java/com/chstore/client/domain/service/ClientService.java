package com.chstore.client.domain.service;

import com.chstore.client.domain.model.Client;
import com.chstore.client.domain.model.vo.Address;
import com.chstore.client.domain.model.vo.ClientId;
import com.chstore.client.domain.model.vo.ClientName;
import com.chstore.client.domain.model.vo.Email;
import java.util.Optional;

public interface ClientService {

    /**
     * Creates a new client.
     * ClientId is typically generated during this process.
     *
     * @param name the client's name
     * @param email the client's email
     * @param address the client's address
     * @return the created client
     */
    Client createClient(ClientName name, Email email, Address address);

    /**
     * Retrieves a client by their ID.
     *
     * @param clientId the ID of the client to retrieve
     * @return an Optional containing the client if found, or empty otherwise
     */
    Optional<Client> getClientById(ClientId clientId);

    /**
     * Updates a client's profile information (e.g., name and address).
     * Email updates might be handled separately due to verification processes.
     *
     * @param clientId the ID of the client to update
     * @param newName the new name for the client
     * @param newAddress the new address for the client
     * @return the updated client
     */
    Client updateClientProfile(ClientId clientId, ClientName newName, Address newAddress);

    /**
     * Deletes a client.
     *
     * @param clientId the ID of the client to delete
     */
    void deleteClient(ClientId clientId);

    // Other potential service methods:
    // - changeClientEmail(ClientId clientId, Email newEmail)
    // - listClients(Pageable pageable)
    // - searchClients(ClientSearchCriteria criteria)
}
