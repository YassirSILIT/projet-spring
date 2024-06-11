package com.iSoftNetwork.payment_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Payment {
    @Id
    private Integer id;
    private BigDecimal amount;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String orderId;

}
