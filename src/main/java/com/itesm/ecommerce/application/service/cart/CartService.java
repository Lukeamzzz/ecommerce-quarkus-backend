package com.itesm.ecommerce.application.service.cart;

import java.util.HashMap;
import java.util.Map;

import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.domain.model.ShoppingCart;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.ProductRepository;
import com.itesm.ecommerce.domain.repository.ShoppingCartHasItemRepository;
import com.itesm.ecommerce.domain.repository.ShoppingCartRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.persistence.repository.ShoppingCartHasItemRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CartService {

    @Inject
    ShoppingCartRepository shoppingCartRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    ProductRepository productRepository;
    @Inject
    ShoppingCartHasItemRepository shoppingCartHasItemRepository;

    @Transactional
    public void addProductToCartService(int productId, int quantity, String firebaseId){
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart sc = shoppingCartRepository.findByUserId(user.getId());
        if(sc == null){
            shoppingCartRepository.createShoppingCart(user.getId());
            sc = shoppingCartRepository.findByUserId(user.getId());
        }
        shoppingCartHasItemRepository.addItemToCart(sc.getId(),productId,quantity);
    }

    @Transactional // This ensures that when a user tries to view their cart without having one, a new cart will be created automatically
    public ShoppingCart getShoppingCart(String firebaseId) {
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart sc = shoppingCartRepository.findByUserId(user.getId());
        if(sc == null){
            shoppingCartRepository.createShoppingCart(user.getId());
            sc = shoppingCartRepository.findByUserId(user.getId());
        }
        return sc;
    }

    @Transactional
    public void removeProductFromCartService(int productId, String firebaseId){
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart sc = shoppingCartRepository.findByUserId(user.getId());
        // If there's a cart, verify the product exists and then remove it
        if(sc != null){
            boolean productExists = sc.getItems().stream()
                .anyMatch(item -> item.getProduct().getId() == productId);
            
            if (!productExists) {
                throw new IllegalArgumentException("Product not found in cart");
            }
            
            shoppingCartHasItemRepository.removeItemFromCart(sc.getId(), productId);
        }
    }

    @Transactional 
    public void clearCartService(String firebaseId){
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart sc = shoppingCartRepository.findByUserId(user.getId());
        // If there's a cart, clear it
        if(sc != null){
            shoppingCartHasItemRepository.clearCart(sc.getId());
        }
    }

    @Transactional
    public void updateProductQuantityInCartService(int productId, int quantity, String firebaseId){
        if (quantity <= 0){
            throw new IllegalArgumentException("The quantity parameter must be greater than 0");
        }

        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart sc = shoppingCartRepository.findByUserId(user.getId());
        // If there's a cart, verify the product exists and then update the quantity
        if(sc != null && quantity > 0){
            boolean productExists = sc.getItems().stream()
                .anyMatch(item -> item.getProduct().getId() == productId);
            
            if (!productExists) {
                throw new IllegalArgumentException("Product not found in cart");
            }
            
            shoppingCartHasItemRepository.updateItemQuantity(sc.getId(), productId, quantity);
        }
    }

    @Transactional
    public Map<String, Object> payCartService(String firebaseId) {
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart sc = shoppingCartRepository.findByUserId(user.getId());
        
        if (sc.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot pay an empty shopping cart");
        }

        // Calculate totals
        double subtotal = calculateSubtotal(sc);
        double total = calculateTotal(subtotal);

        // Update the cart status to paid
        shoppingCartRepository.updateCartStatus(sc.getId(), "paid");

        // Create response with payment details
        Map<String, Object> paymentDetails = new HashMap<>();
        
        paymentDetails.put("total", total);
        paymentDetails.put("subtotal", subtotal);
        paymentDetails.put("message", "Payment successful");
        
        return paymentDetails;
    }

    private double calculateSubtotal(ShoppingCart cart) {
        return cart.getItems().stream()
            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
            .sum();
    }

    private double calculateTotal(double subtotal) {
        double taxRate = 0.16;
        return subtotal * (1 + taxRate);
    }
}
