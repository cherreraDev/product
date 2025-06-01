package com.chstore.product.application;

import com.chstore.product.domain.event.ClientEventPublisher;
import com.chstore.product.domain.model.Client;
import com.chstore.product.domain.model.vo.ClientId;
import com.chstore.product.domain.repository.ClientRepository;
import com.chstore.product.domain.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultClientService implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientEventPublisher eventPublisher;

    @Override
    public Client save(Client client) {
        boolean exists = clientRepository.findById(client.getId()).isPresent();
        Client saved = clientRepository.save(client);

        if (exists) {
            eventPublisher.publishClientUpdated(saved);
        } else {
            eventPublisher.publishClientCreated(saved);
        }

        return saved;
    }

    @Override
    public Optional<Client> getById(ClientId id) {
        return this.clientRepository.findById(id);
    }

    @Override
    public void deleteById(ClientId id) {
        Optional<Client> clientOpt = this.clientRepository.findById(id);
        clientOpt.ifPresent(client -> {
            this.clientRepository.deleteById(id);
            eventPublisher.publishClientDeleted(client);
        });
    }
}