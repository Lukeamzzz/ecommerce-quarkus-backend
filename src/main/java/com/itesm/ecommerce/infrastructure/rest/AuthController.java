package com.itesm.ecommerce.infrastructure.rest;

import java.util.HashMap;
import java.util.Map;

import com.itesm.ecommerce.application.useCase.auth.SignUpUseCase;
import com.itesm.ecommerce.infrastructure.dto.auth.SignUpDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthController {
    @Inject
    SignUpUseCase signUpUseCase;

    @POST
    @Path("/signup")
    public Response signUp(SignUpDTO signUpDTO) {
        try {
            signUpUseCase.execute(signUpDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User created successfully");
            return Response.ok().entity(response).build();
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }
    }
}
