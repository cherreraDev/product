package com.chstore.product.infrastructure.http.dto;

import java.util.UUID;

public record ClientRequest(
    UUID id,
    String name,
    String email,
    String phone,
    String street,
    String city,
    String state,
    String zipCode,
    String country
) {}