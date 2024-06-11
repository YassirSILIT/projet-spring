package com.iSoftNetwork.payment_service.entities;

import com.iSoftNetwork.payment_service.model.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentResponse(
        Integer id,
        BigDecimal amount,
        LocalDate date,
        PaymentMethod paymentMethod,
        String orderId,
        Customer customer
) {
}
