package com.iSoftNetwork.payment_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
         Long id,
         @NotNull(message = "FirstName is required")
         String firstName,
         @NotNull(message = "LastName is required")
         String lastName,
         @NotNull(message = "Phone is required")
         String phone,
         @Email(message = "The customer email is not correctly formatted")
         String email
         ) {
}
