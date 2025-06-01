package com.chstore.product.domain.exception;

import com.chstore.product.domain.model.vo.ClientId;

public class ClientDeleteException extends RuntimeException {
    public ClientDeleteException(ClientId id) {
        super("Error deleting client with id: " + id.getValue());
    }

    public ClientDeleteException(ClientId id, Throwable cause) {
        super("Error deleting client with id: " + id.getValue(), cause);
    }
}