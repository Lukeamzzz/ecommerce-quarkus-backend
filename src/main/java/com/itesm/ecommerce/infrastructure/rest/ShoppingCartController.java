package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.useCase.cart.AddProductToCartUseCase;
import com.itesm.ecommerce.application.useCase.cart.ClearCartUseCase;
import com.itesm.ecommerce.application.useCase.cart.FindUserShoppingCartUseCase;
import com.itesm.ecommerce.application.useCase.cart.PayCartUseCase;
import com.itesm.ecommerce.application.useCase.cart.RemoveProductFromCartUseCase;
import com.itesm.ecommerce.application.useCase.cart.UpdateProductQuantitytInCartUseCase;
import com.itesm.ecommerce.infrastructure.dto.cart.AddProductToCartDTO;
import com.itesm.ecommerce.infrastructure.dto.cart.RemoveProductFromCartDTO;
import com.itesm.ecommerce.infrastructure.dto.cart.UpdateProductQuantityInCartDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.annotations.Message;

import java.util.HashMap;
import java.util.Map;

@Path("/cart")
public class ShoppingCartController {
    @Inject
    AddProductToCartUseCase addProductToCartUseCase;
    @Inject
    FindUserShoppingCartUseCase findUserShoppingCartUseCase;
    @Inject
    RemoveProductFromCartUseCase removeProductFromCartUseCase;
    @Inject
    ClearCartUseCase clearCartUseCase;
    @Inject
    UpdateProductQuantitytInCartUseCase updateProductQuantitytInCartUseCase;
    @Inject
    PayCartUseCase payCartUseCase;

    @POST
    @Path("/add")
    public Response addProductToCart(AddProductToCartDTO addProductToCartDTO) {
        // Call the use case to add the product to the cart
        addProductToCartUseCase.execute(addProductToCartDTO, "tpg1yjNhoPOqqBY9W25CPCVU5zi1"); // UUID from firebase
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product added to cart successfully");
        return Response.ok().entity(response).build();
    }

    @GET
    public Response getShoppingCart() {
        // Call the use case to get the shopping cart
        return Response.ok().entity(findUserShoppingCartUseCase.execute("tpg1yjNhoPOqqBY9W25CPCVU5zi1")).build();
    }

    @DELETE
    @Path("/remove")
    public Response removeProductFromCart(RemoveProductFromCartDTO removeProductFromCartDTO){
        // Call the use case to remove the product from the cart
        removeProductFromCartUseCase.execute(removeProductFromCartDTO, "tpg1yjNhoPOqqBY9W25CPCVU5zi1"); 
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product removed from cart successfully");
        return Response.ok().entity(response).build();
    }

    @DELETE
    @Path("/clear")
    public Response clearCart(){
        // Call the use case to clear the cart
        clearCartUseCase.execute("tpg1yjNhoPOqqBY9W25CPCVU5zi1");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cart cleared successfully");
        return Response.ok().entity(response).build();
    }

    @PATCH
    @Path("/update-quantity")
    public Response updateProductQuantityInCart(UpdateProductQuantityInCartDTO updateProductQuantityInCartDTO){
        // Call the use case to clear the cart
        updateProductQuantitytInCartUseCase.execute(updateProductQuantityInCartDTO, "tpg1yjNhoPOqqBY9W25CPCVU5zi1");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product quantity updated successfully");
        return Response.ok().entity(response).build();
    }

    @POST
    @Path("/checkout")
    public Response checkout(){
        // Call the use case to pay the cart and get the subtotal and total in the response
        Map<String, Object> paymentDetails = payCartUseCase.execute("tpg1yjNhoPOqqBY9W25CPCVU5zi1");
        return Response.ok().entity(paymentDetails).build();
    }
}
