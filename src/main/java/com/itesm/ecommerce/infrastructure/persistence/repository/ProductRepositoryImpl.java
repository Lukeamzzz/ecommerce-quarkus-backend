package com.itesm.ecommerce.infrastructure.persistence.repository;

import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.infrastructure.persistence.entity.ProductEntity;
import com.itesm.ecommerce.infrastructure.persistence.mapper.ProductMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import com.itesm.ecommerce.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository, PanacheRepositoryBase<ProductEntity, Integer> {

    @Override
    @Transactional
    public Product insertProduct(Product product) {
        persist(ProductMapper.toEntity(product));
        flush();
        return product;
    }
}
