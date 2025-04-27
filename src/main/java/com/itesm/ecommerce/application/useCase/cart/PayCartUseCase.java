package com.itesm.ecommerce.application.useCase.cart;

import com.itesm.ecommerce.application.service.cart.CartService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class PayCartUseCase {
    @Inject
    CartService cartService;

    // Map<String, Object> is needed since we want to return the subtotal and total as a response when the endpoint is called
    public Map<String, Object> execute(String firebaseId){
        return cartService.payCartService(firebaseId);
    }
}
