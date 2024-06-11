package com.iSoftNetwork.inventoryservice.dto;


import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class CategoryDTO {
    private Long id;
    private String code;
    private String designation;
    //private List<ProductDTO> products;
}
