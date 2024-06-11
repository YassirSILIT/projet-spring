package com.iSoftNetwork.ordersmservice.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder @ToString
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
