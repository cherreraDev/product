package com.chstore.client.domain.repository;

import com.chstore.client.domain.model.Client;
import com.chstore.client.domain.model.vo.ClientId;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    /**
     * Saves a client.
     *
     * @param client the client to save
     */
    void save(Client client);

    /**
     * Finds a client by its ID.
     *
     * @param clientId the ID of the client
     * @return an Optional containing the client if found, or empty otherwise
     */
    Optional<Client> findById(ClientId clientId);

    /**
     * Deletes a client by its ID.
     *
     * @param clientId the ID of the client to delete
     */
    void delete(ClientId clientId);

    /**
     * Finds all clients.
     * Note: For large datasets, consider using pagination or search criteria.
     *
     * @return a list of all clients
     */
    List<Client> findAll();

    // Consider adding methods with search criteria or pagination in a real application
    // e.g., List<Client> findByCriteria(ClientSearchCriteria criteria);
    // e.g., Page<Client> findAll(Pageable pageable);
}
