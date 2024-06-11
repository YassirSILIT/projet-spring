package com.ensak.customerMservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
