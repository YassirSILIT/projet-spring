package com.iSoftNetwork.ordersmservice.dto;

import com.iSoftNetwork.ordersmservice.entities.Order;
import com.iSoftNetwork.ordersmservice.model.Product;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder @ToString
public class ProductItemsRequestDTO {
    private Long id;
    private int quantity;
    private double price;
    private double discount;
    private Long productId;
    private Product product;
    private Order order;
}
