package com.iSoftNetwork.ordersmservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iSoftNetwork.ordersmservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder @ToString
@Table(name = "T-ORDERS")
public class Order {
    @Id
    private String id;
    private LocalDate date;
    private Long customerId;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "order")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductItems> productItems;

    //Total orders
    public double getTotal(){
        double somme = 0;
        for(ProductItems pri : productItems){
            somme+=pri.getAmount();
        }
        return somme;
    }
}
