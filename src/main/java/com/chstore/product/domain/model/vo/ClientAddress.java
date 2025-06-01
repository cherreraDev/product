package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidClientValueObjectException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ClientAddress {
    private final String street;
    private final String city;
    private final String state;
    private final String zipCode;
    private final String country;

    public ClientAddress(String street, String city, String state, String zipCode, String country) {
        if (street == null || street.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Street cannot be null or empty");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("City cannot be null or empty");
        }
        if (state == null || state.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("State cannot be null or empty");
        }
        if (zipCode == null || zipCode.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Zip code cannot be null or empty");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Country cannot be null or empty");
        }
        
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s %s, %s", street, city, state, zipCode, country);
    }
}