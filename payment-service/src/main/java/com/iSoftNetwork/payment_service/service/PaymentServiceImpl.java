package com.iSoftNetwork.payment_service.service;

import com.iSoftNetwork.payment_service.entities.Payment;
import com.iSoftNetwork.payment_service.entities.PaymentRequest;
import com.iSoftNetwork.payment_service.entities.PaymentResponse;
import com.iSoftNetwork.payment_service.mapper.PaymentMapper;
import com.iSoftNetwork.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }


    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment savedPayment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        return null;
    }
}
