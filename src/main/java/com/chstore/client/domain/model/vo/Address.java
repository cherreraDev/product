package com.chstore.client.domain.model.vo;

import com.chstore.client.domain.exception.InvalidClientValueObjectException;
import java.util.Objects;

public final class Address {

    private final String street;
    private final String city;
    private final String postalCode;
    private final String country;

    public Address(String street, String city, String postalCode, String country) {
        if (street == null || street.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Street cannot be null or empty");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("City cannot be null or empty");
        }
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Postal code cannot be null or empty");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Country cannot be null or empty");
        }

        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
               Objects.equals(city, address.city) &&
               Objects.equals(postalCode, address.postalCode) &&
               Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, postalCode, country);
    }

    @Override
    public String toString() {
        return "Address{" +
               "street='" + street + '\'' +
               ", city='" + city + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}
