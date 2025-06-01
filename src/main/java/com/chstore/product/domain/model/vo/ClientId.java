package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidClientValueObjectException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ClientId {
    private final UUID value;

    public ClientId(UUID value) {
        if (value == null) {
            throw new InvalidClientValueObjectException("Client id cannot be null");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
