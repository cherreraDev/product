package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidClientValueObjectException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class ClientPhone {
    private final String value;
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{6,15}$");

    public ClientPhone(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Client phone cannot be null or empty");
        }
        String normalizedPhone = value.replaceAll("[\\s-()]", "");
        if (!PHONE_PATTERN.matcher(normalizedPhone).matches()) {
            throw new InvalidClientValueObjectException("Invalid phone number format");
        }
        this.value = normalizedPhone;
    }

    @Override
    public String toString() {
        return value;
    }
}