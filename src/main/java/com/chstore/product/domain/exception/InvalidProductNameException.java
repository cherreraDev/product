package com.chstore.product.domain.exception;

public class InvalidProductNameException extends RuntimeException{
    public InvalidProductNameException(String message) {
        super(message);
    }
}
