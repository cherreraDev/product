package com.chstore.product.domain.exception;

public class ClientSaveException extends RuntimeException {
    public ClientSaveException(String id) {
        super("Error saving client with id: " + id);
    }

    public ClientSaveException(String id, Throwable cause) {
        super("Error saving client with id: " + id, cause);
    }
}