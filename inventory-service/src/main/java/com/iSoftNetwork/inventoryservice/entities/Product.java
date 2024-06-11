package com.iSoftNetwork.inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 3)
    private String name;
    @NotEmpty
    @Size(min = 3)
    private int quantity;
    @NotEmpty
    @Size(min = 3)
    private double priceHT;
    @NotEmpty
    @Size(min = 3)
    private double tva;
    @NotEmpty
    @Size(min = 3)
    private double priceTtc;
    @ManyToOne
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;

}
