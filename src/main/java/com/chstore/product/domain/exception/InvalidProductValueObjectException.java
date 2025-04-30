package com.chstore.product.domain.exception;

public class InvalidProductValueObjectException extends RuntimeException{
    public InvalidProductValueObjectException(String message) {
        super(message);
    }
}
