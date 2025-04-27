package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.ShoppingCart;

public interface ShoppingCartRepository {
    ShoppingCart findByUserId(int userId);
    void createShoppingCart(int userId);
    void updateCartStatus(int cartId, String status);
}
