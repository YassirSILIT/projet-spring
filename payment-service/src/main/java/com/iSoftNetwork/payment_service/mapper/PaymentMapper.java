package com.iSoftNetwork.payment_service.mapper;

import com.iSoftNetwork.payment_service.entities.Payment;
import com.iSoftNetwork.payment_service.entities.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest){
        return Payment.builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .build();
    }

}
