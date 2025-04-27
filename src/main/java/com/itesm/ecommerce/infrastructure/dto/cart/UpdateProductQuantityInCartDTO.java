package com.itesm.ecommerce.infrastructure.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductQuantityInCartDTO {
    private int productId;
    private int quantity; 
}
