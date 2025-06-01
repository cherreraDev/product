package com.chstore.product.domain.exception;

import com.chstore.product.domain.model.vo.ClientId;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(ClientId id) {
        super("Client not found with id: " + id.getValue());
    }

    public ClientNotFoundException(ClientId id, Throwable cause) {
        super("Client not found with id: " + id.getValue(), cause);
    }
}