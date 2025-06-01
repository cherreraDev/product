package com.chstore.product.infrastructure.persistence;

import com.chstore.product.domain.exception.ProductDeleteException;
import com.chstore.product.domain.exception.ProductNotFoundException;
import com.chstore.product.domain.exception.ProductSaveException;
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
public class MongoProductRepository implements ProductRepository {
    private final SpringDataProductMongoRepository mongoRepository;
    @Override
    public Product save(Product product) {
        try {
            ProductEntity productEntity = this.mongoRepository.save(ProductMapper.INSTANCE.toEntity(product));
            return ProductMapper.INSTANCE.toDomain(productEntity);
        } catch (RuntimeException e){
            throw new ProductSaveException("id: " + product.getId().getValue(), e);
        }
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        try {
            Optional<ProductEntity> productEntity = this.mongoRepository.findById(id.getValue());

            return productEntity.map(ProductMapper.INSTANCE::toDomain);
        } catch (RuntimeException e){
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public void deleteById(ProductId id) {
        try {
            this.mongoRepository.deleteById(id.getValue());
        } catch (RuntimeException e){
            throw new ProductDeleteException(id, e);
        }
    }
}
