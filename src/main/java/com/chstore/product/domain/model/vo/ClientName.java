package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidClientValueObjectException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ClientName {
    private final String value;
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 100;

    public ClientName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Client name cannot be null or empty");
        }
        if (value.length() < MIN_LENGTH) {
            throw new InvalidClientValueObjectException("Client name must be at least " + MIN_LENGTH + " characters");
        }
        if (value.length() > MAX_LENGTH) {
            throw new InvalidClientValueObjectException("Client name cannot exceed " + MAX_LENGTH + " characters");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}