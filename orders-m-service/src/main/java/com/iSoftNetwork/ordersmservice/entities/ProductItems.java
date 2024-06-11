package com.iSoftNetwork.ordersmservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iSoftNetwork.ordersmservice.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder @ToString
public class ProductItems {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Long productId;
    private double price;
    private double discount;
    @Transient
    private Product product;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;

    public double getAmount(){
        return quantity*price*(1-(discount/100));
    }

}
