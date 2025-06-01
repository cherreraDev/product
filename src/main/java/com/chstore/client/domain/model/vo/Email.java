package com.chstore.client.domain.model.vo;

import com.chstore.client.domain.exception.InvalidClientValueObjectException;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Email {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    private final String address;

    public Email(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Email address cannot be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(address).matches()) {
            throw new InvalidClientValueObjectException("Invalid email address format: " + address);
        }
        this.address = address;
    }

    public String getValue() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Email{" +
               "address='" + address + '\'' +
               '}';
    }
}
