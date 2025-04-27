package com.itesm.ecommerce.application.useCase.cart;

import com.itesm.ecommerce.application.service.cart.CartService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClearCartUseCase {
    @Inject 
    CartService cartService;

    public void execute(String firebaseId){
        cartService.clearCartService(firebaseId);
    }
}
