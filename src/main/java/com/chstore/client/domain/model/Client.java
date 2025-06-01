package com.chstore.client.domain.model;

import com.chstore.client.domain.model.vo.Address;
import com.chstore.client.domain.model.vo.ClientId;
import com.chstore.client.domain.model.vo.ClientName;
import com.chstore.client.domain.model.vo.Email;
import java.util.Objects;

public class Client {

    private final ClientId id;
    private final ClientName name;
    private final Email email;
    private final Address address;

    private Client(ClientId id, ClientName name, Email email, Address address) {
        if (id == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Client name cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public static Client create(ClientId id, ClientName name, Email email, Address address) {
        return new Client(id, name, email, address);
    }

    public ClientId getId() {
        return id;
    }

    public ClientName getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Client changeName(ClientName newName) {
        return new Client(this.id, newName, this.email, this.address);
    }

    public Client changeEmail(Email newEmail) {
        return new Client(this.id, this.name, newEmail, this.address);
    }

    public Client changeAddress(Address newAddress) {
        return new Client(this.id, this.name, this.email, newAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
               "id=" + id +
               ", name=" + name +
               ", email=" + email +
               ", address=" + address +
               '}';
    }
}
