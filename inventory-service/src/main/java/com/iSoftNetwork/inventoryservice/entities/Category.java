package com.iSoftNetwork.inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Category {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 3)
    private String code;
    @NotEmpty
    @Size(min = 3)
    private String designation;
    @OneToMany(mappedBy = "category")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Product> products;
}
