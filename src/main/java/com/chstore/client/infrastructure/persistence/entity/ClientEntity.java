package com.chstore.client.infrastructure.persistence.entity;

import com.chstore.client.domain.model.Client;
import com.chstore.client.domain.model.vo.Address;
import com.chstore.client.domain.model.vo.ClientId;
import com.chstore.client.domain.model.vo.ClientName;
import com.chstore.client.domain.model.vo.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
public class ClientEntity {

    @Id
    private String id; // Corresponds to ClientId.getValue()

    private String name; // Corresponds to ClientName.getValue()
    private String email; // Corresponds to Email.getValue()

    // Address components
    private String street;
    private String city;
    private String postalCode;
    private String country;

    // Default no-args constructor
    public ClientEntity() {
    }

    // Constructor with all fields
    public ClientEntity(String id, String name, String email, String street, String city, String postalCode, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // --- Mapping Methods ---

    public static ClientEntity fromDomain(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientEntity(
                client.getId().getValue(),
                client.getName().getValue(),
                client.getEmail().getValue(),
                client.getAddress().getStreet(),
                client.getAddress().getCity(),
                client.getAddress().getPostalCode(),
                client.getAddress().getCountry()
        );
    }

    public Client toDomain() {
        // VOs will perform their own validation upon instantiation
        ClientId clientId = new ClientId(this.id);
        ClientName clientName = new ClientName(this.name);
        Email clientEmail = new Email(this.email);
        Address clientAddress = new Address(this.street, this.city, this.postalCode, this.country);

        return Client.create(clientId, clientName, clientEmail, clientAddress);
    }
}
