package com.ensak.customerMservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class CustomerDTO {
    private Long id;
    @NotEmpty
    @Size(min = 3)
    private String firstName;
    @NotEmpty
    @Size(min = 3)
    private String lastName;
    @NotEmpty
    @Size(min = 8)
    private String email;
    @NotEmpty
    @Size(min = 10)
    private String phone;

}
