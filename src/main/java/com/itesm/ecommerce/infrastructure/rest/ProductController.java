package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.useCase.CreateProductUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import com.itesm.ecommerce.domain.model.Product;
import jakarta.ws.rs.core.Response;

@Path("products")
@Produces("application/json")
@Consumes("application/json")
public class ProductController {
    @Inject
    CreateProductUseCase createProductUseCase;

    @POST
    public Response createProduct(Product product) {
        product=createProductUseCase.execute(product);
        return Response.ok(product).build();
    }
}
