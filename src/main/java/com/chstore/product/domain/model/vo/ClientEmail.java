package com.chstore.product.domain.model.vo;

import com.chstore.product.domain.exception.InvalidClientValueObjectException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class ClientEmail {
    private final String value;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    public ClientEmail(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidClientValueObjectException("Client email cannot be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new InvalidClientValueObjectException("Invalid email format");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}