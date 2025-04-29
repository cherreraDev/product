package com.chstore.product.application;

import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;
import com.chstore.product.domain.repository.ProductRepository;
import com.chstore.product.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Optional<Product> getById(ProductId id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void deleteById(ProductId id) {
        this.productRepository.deleteById(id);
    }
}
