package com.chstore.product.domain.model;

import com.chstore.product.domain.model.vo.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client {
    private final ClientId id;
    private final ClientName name;
    private final ClientEmail email;
    private final ClientPhone phone;
    private ClientAddress address;

    public void updateAddress(ClientAddress newAddress) {
        if(newAddress == null) {
            throw new IllegalArgumentException("address cannot be null");
        }
        this.address = newAddress;
    }
}