package com.chstore.product.domain.exception;

public class ProductSaveException extends RuntimeException{
    public ProductSaveException(String message) {
        super("Error saving product: " + message);
    }

    public ProductSaveException(String message, Throwable cause) {
        super("Error saving product: " + message, cause);
    }
}
