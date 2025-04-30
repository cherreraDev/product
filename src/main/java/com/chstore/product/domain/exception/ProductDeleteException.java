package com.chstore.product.domain.exception;

import com.chstore.product.domain.model.vo.ProductId;

public class ProductDeleteException extends RuntimeException{
    public ProductDeleteException(ProductId id, Throwable cause) {
        super("Error deleting product with id: " + id.getValue(), cause);
    }
}
