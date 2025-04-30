package com.chstore.product.infrastructure.http;

import com.chstore.product.domain.exception.InvalidProductValueObjectException;
import com.chstore.product.domain.exception.ProductDeleteException;
import com.chstore.product.domain.exception.ProductNotFoundException;
import com.chstore.product.domain.exception.ProductSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidProductValueObjectException.class)
    public ResponseEntity<String> handleInvalidValueObjectException(InvalidProductValueObjectException ex) {
        return new ResponseEntity<>("Error at entry fields: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductSaveException.class)
    public ResponseEntity<String> handleSaveError(ProductSaveException ex) {
        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductDeleteException.class)
    public ResponseEntity<String> handleDeleteError(ProductDeleteException ex) {
        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
