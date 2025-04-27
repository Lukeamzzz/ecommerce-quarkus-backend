package com.itesm.ecommerce.application.useCase.cart;

import com.itesm.ecommerce.application.service.cart.CartService;
import com.itesm.ecommerce.infrastructure.dto.cart.RemoveProductFromCartDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveProductFromCartUseCase {
    @Inject
    CartService cartService;

    public void execute(RemoveProductFromCartDTO removeProductFromCartDTO, String firebaseId){
        cartService.removeProductFromCartService(removeProductFromCartDTO.getProductId(), firebaseId);
    }
}
