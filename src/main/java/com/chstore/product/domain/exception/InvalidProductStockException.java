package com.chstore.product.domain.exception;

public class InvalidProductStockException extends RuntimeException{
    public InvalidProductStockException(String message){
        super(message);
    }
}
