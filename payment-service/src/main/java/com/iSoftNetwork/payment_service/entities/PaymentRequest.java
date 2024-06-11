package com.iSoftNetwork.payment_service.entities;

import com.iSoftNetwork.payment_service.model.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        LocalDate date,
        String orderId,
        Customer customer
) {
}
