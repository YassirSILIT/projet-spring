package com.iSoftNetwork.ordersmservice.dto;

import com.iSoftNetwork.ordersmservice.entities.OrderState;
import com.iSoftNetwork.ordersmservice.entities.ProductItems;
import com.iSoftNetwork.ordersmservice.model.Customer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder @ToString
public class OrderResponseDTO {
    private String id;
    private LocalDate date;
    private Long customerId;
    private OrderState state;
    private Customer customer;
    //private List<ProductItems> productItems;
}
