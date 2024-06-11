package com.iSoftNetwork.ordersmservice.dto;

import com.iSoftNetwork.ordersmservice.entities.OrderState;
import com.iSoftNetwork.ordersmservice.model.Customer;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder @ToString
public class OrderRequestDTO {
    private String id;
    private LocalDate date;
    private Long customerId;
    private OrderState state;
    private Customer customer;
}
