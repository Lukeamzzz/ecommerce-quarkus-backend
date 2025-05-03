package com.itesm.ecommerce.application.useCase.auth;

import com.google.firebase.auth.UserRecord;
import com.itesm.ecommerce.application.service.SignUpService;
import com.itesm.ecommerce.infrastructure.dto.auth.SignUpDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class SignUpUseCase {
    @Inject
    SignUpService signUpService;

    public void execute(SignUpDTO signUpDTO) throws Exception {
        signUpService.signUp(signUpDTO.getEmail(), signUpDTO.getPassword());
    }
}
