package com.iSoftNetwork.inventoryservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class ProductDTO {
    private Long id;
    private String name;
    private int quantity;
    private double priceHT;
    private double tva;
    private double priceTtc;
    private CategoryDTO category;

}
