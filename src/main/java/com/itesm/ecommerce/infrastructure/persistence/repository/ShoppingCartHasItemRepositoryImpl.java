package com.itesm.ecommerce.infrastructure.persistence.repository;

import com.itesm.ecommerce.domain.model.ShoppingCart;
import com.itesm.ecommerce.domain.repository.ShoppingCartHasItemRepository;
import com.itesm.ecommerce.domain.repository.ShoppingCartRepository;
import com.itesm.ecommerce.infrastructure.persistence.entity.ShoppingCartEntity;
import com.itesm.ecommerce.infrastructure.persistence.entity.ShoppingCartHasProductsEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ShoppingCartHasItemRepositoryImpl implements ShoppingCartHasItemRepository, PanacheRepository<ShoppingCartHasProductsEntity> {

    @Inject
    UserRepositoryImpl userRepository;
    @Inject
    ProductRepositoryImpl productRepository;
    @Inject
    ShoppingCartRepositoryImpl shoppingCartRepository;


    @Override
    public void addItemToCart(int shoppingCartId, int productId, int quantity) {
        ShoppingCartHasProductsEntity shoppingCartHasProductsEntity = new ShoppingCartHasProductsEntity();
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findByIdEntity(shoppingCartId);
        shoppingCartHasProductsEntity.setProduct(productRepository.findById(productId));
        shoppingCartHasProductsEntity.setShoppingCart(shoppingCartEntity);
        shoppingCartHasProductsEntity.setQuantity(quantity);
        persist(shoppingCartHasProductsEntity);
    }

    @Override
    public void removeItemFromCart(int shoppingCartId, int productId) {
        // ?1 and ?2 are placeholders for the sequentially numbered parameters passed to the method
        // ?1 will always be the first parameter, ?2 will always be the second parameter, and so on
        delete("shoppingCart.id = ?1 and product.id = ?2", shoppingCartId, productId);
    }

    @Override
    public void updateItemQuantity(int shoppingCartId, int productId, int quantity) {
        // Update the quantity of the item in the shopping cart where the parameters match
        update("quantity = ?3 where shoppingCart.id = ?1 and product.id = ?2", shoppingCartId, productId, quantity);
    }

    @Override
    public void clearCart(int shoppingCartId) {
        // Remove all items from the shopping cart where the parameter matches
        delete("shoppingCart.id = ?1", shoppingCartId);
    }
}
