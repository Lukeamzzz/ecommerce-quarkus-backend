package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.itesm.ecommerce.domain.repository.ProductRepository;
@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository repository;

    public Product createProduct(Product product) {
        return repository.insertProduct(product);
    }
}
