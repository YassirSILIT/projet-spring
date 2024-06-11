package com.iSoftNetwork.payment_service.repository;

import com.iSoftNetwork.payment_service.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
