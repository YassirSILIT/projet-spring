package com.iSoftNetwork.payment_service.service;

import com.iSoftNetwork.payment_service.entities.PaymentRequest;
import com.iSoftNetwork.payment_service.entities.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);
}
