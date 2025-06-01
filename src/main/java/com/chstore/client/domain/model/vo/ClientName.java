package com.chstore.client.domain.model.vo;

import com.chstore.client.domain.exception.InvalidClientValueObjectException;
import java.util.Objects;

public final class ClientName {

    private final String name;

    public ClientName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Client name cannot be null or empty");
        }
        this.name = name;
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientName that = (ClientName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ClientName{" +
               "name='" + name + '\'' +
               '}';
    }
}
