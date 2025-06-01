package com.chstore.client.domain.model.vo;

import java.util.Objects;

// For now, assume com.chstore.client.domain.exception.InvalidClientValueObjectException exists
import com.chstore.client.domain.exception.InvalidClientValueObjectException;

public final class ClientId {

    private final String id;

    public ClientId(String id) {
        if (id == null || id.trim().isEmpty()) {
            // Replace with InvalidClientValueObjectException when available
            throw new InvalidClientValueObjectException("Client ID cannot be null or empty");
        }
        this.id = id;
    }

    public String getValue() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientId clientId = (ClientId) o;
        return Objects.equals(id, clientId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ClientId{" +
               "id='" + id + '\'' +
               '}';
    }
}
