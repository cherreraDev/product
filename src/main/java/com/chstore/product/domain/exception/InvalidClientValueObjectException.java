package com.chstore.product.domain.exception;

public class InvalidClientValueObjectException extends RuntimeException {
    public InvalidClientValueObjectException(String message) {
        super(message);
    }

    public InvalidClientValueObjectException(String message, Throwable cause) {
        super(message, cause);
    }
}