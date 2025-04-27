package com.itesm.ecommerce.application.useCase.cart;

import com.itesm.ecommerce.application.service.cart.CartService;
import com.itesm.ecommerce.infrastructure.dto.cart.UpdateProductQuantityInCartDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateProductQuantitytInCartUseCase {
    @Inject
    CartService cartService;

    public void execute(UpdateProductQuantityInCartDTO updateProductQuantityInCartDTO, String firebaseId){
        cartService.updateProductQuantityInCartService(updateProductQuantityInCartDTO.getProductId(), 
        updateProductQuantityInCartDTO.getQuantity(), firebaseId);
    }
}
