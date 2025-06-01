package com.chstore.client.application;

import com.chstore.client.domain.exception.ClientNotFoundException;
import com.chstore.client.domain.model.Client;
import com.chstore.client.domain.model.vo.Address;
import com.chstore.client.domain.model.vo.ClientId;
import com.chstore.client.domain.model.vo.ClientName;
import com.chstore.client.domain.model.vo.Email;
import com.chstore.client.domain.repository.ClientRepository;
import com.chstore.client.domain.service.ClientService;
import com.chstore.client.domain.event.ClientEventPublisher; // Import the event publisher
import org.springframework.stereotype.Service; // Assuming Spring is a dependency

import java.util.Optional;
import java.util.UUID;

@Service // Marks this as a Spring service component
public class DefaultClientService implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientEventPublisher eventPublisher; // Add field for event publisher

    // Updated constructor injection
    public DefaultClientService(ClientRepository clientRepository, ClientEventPublisher eventPublisher) {
        if (clientRepository == null) {
            throw new IllegalArgumentException("ClientRepository cannot be null");
        }
        if (eventPublisher == null) {
            throw new IllegalArgumentException("ClientEventPublisher cannot be null");
        }
        this.clientRepository = clientRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Client createClient(ClientName name, Email email, Address address) {
        if (name == null || email == null || address == null) {
            throw new IllegalArgumentException("Client name, email, and address must not be null");
        }
        // Generate a new ClientId
        ClientId clientId = new ClientId(UUID.randomUUID().toString());

        // Create a new Client instance
        Client client = Client.create(clientId, name, email, address);

        // Save the client using the repository
        clientRepository.save(client);

        // Publish client created event
        eventPublisher.publishClientCreatedEvent(client);

        return client;
    }

    @Override
    public Optional<Client> getClientById(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("ClientId cannot be null");
        }
        return clientRepository.findById(clientId);
    }

    @Override
    public Client updateClientProfile(ClientId clientId, ClientName newName, Address newAddress) {
        if (clientId == null || newName == null || newAddress == null) {
            throw new IllegalArgumentException("Client ID, new name, and new address must not be null");
        }

        // Fetch the existing client by ID
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + clientId.getValue()));

        // Use the client's update methods
        Client updatedClient = existingClient
            .changeName(newName)
            .changeAddress(newAddress);

        // Save the updated client using the repository
        clientRepository.save(updatedClient);

        // Publish client updated event
        eventPublisher.publishClientUpdatedEvent(updatedClient);

        return updatedClient;
    }

    @Override
    public void deleteClient(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("ClientId cannot be null");
        }
         // Optional: Check if client exists before attempting deletion
        if (!clientRepository.findById(clientId).isPresent()) {
            throw new ClientNotFoundException("Client not found with ID: " + clientId.getValue() + ". Cannot delete.");
        }
        clientRepository.delete(clientId);

        // Publish client deleted event
        eventPublisher.publishClientDeletedEvent(clientId);
    }
}
