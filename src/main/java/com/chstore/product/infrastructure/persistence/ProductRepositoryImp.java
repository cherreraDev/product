package com.chstore.product.infrastructure.persistence;

import com.chstore.product.domain.model.Product;
import com.chstore.product.domain.model.vo.ProductId;
import com.chstore.product.domain.repository.ProductRepository;
import com.chstore.product.infrastructure.persistence.entity.ProductEntity;
import com.chstore.product.infrastructure.persistence.entity.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class ProductRepositoryImp implements ProductRepository {
    private final ProductMongoRepository mongoRepository;
    @Override
    public Product save(Product product) {
        ProductEntity productEntity = this.mongoRepository.save(ProductMapper.INSTANCE.toEntity(product));
        return ProductMapper.INSTANCE.toDomain(productEntity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        Optional<ProductEntity> productEntity = this.mongoRepository.findById(id.getValue());

        return productEntity.map(ProductMapper.INSTANCE::toDomain);
    }

    @Override
    public void deleteById(ProductId id) {
        this.mongoRepository.deleteById(id.getValue());
    }
}
