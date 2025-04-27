package com.chstore.product.domain.exception;

public class InvalidProductIdException extends RuntimeException{
    public InvalidProductIdException(String message) {
        super(message);
    }
}
