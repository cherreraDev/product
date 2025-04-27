package com.chstore.product.domain.event;

import com.chstore.product.domain.model.Product;

public interface ProductEventPublisher {
    void publishProductCreated(Product product);
    void publishProductUpdated(Product product);
    void publishProductDeleted(Product product);
}
