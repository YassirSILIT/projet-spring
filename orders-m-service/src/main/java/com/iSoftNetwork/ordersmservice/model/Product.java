package com.iSoftNetwork.ordersmservice.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder @ToString
public class Product {
    private Long id;
    private String name;
    private int quantity;
    private double priceHT;
    private double tva;
    private double priceTtc;
}
