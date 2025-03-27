package com.itesm.ecommerce.domain.repository;


import com.itesm.ecommerce.domain.model.Product;

public interface ProductRepository {
    public Product insertProduct(Product product);
}
