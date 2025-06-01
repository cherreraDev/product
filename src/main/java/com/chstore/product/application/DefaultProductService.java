package com.chstore.product.application;

import com.chstore.product.domain.event.ProductEventPublisher;
import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;
import com.chstore.product.domain.repository.ProductRepository;
import com.chstore.product.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;
    private final ProductEventPublisher eventPublisher;
    @Override
    public Product save(Product product) {

        boolean exists = productRepository.findById(product.getId()).isPresent();
        Product saved = productRepository.save(product);

        if (exists) {
            eventPublisher.publishProductUpdated(saved);
        } else {
            eventPublisher.publishProductCreated(saved);
        }

        return saved;
    }

    @Override
    public Optional<Product> getById(ProductId id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void deleteById(ProductId id) {
        Optional<Product> productOpt = this.productRepository.findById(id);
        productOpt.ifPresent(product -> {
            this.productRepository.deleteById(id);
            eventPublisher.publishProductDeleted(product);
        });
    }
}
