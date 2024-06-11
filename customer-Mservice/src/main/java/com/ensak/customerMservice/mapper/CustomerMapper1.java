package com.ensak.customerMservice.mapper;

import com.ensak.customerMservice.dto.CustomerDTO;
import com.ensak.customerMservice.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper1 {
    ModelMapper modelMapper = new ModelMapper();
    public CustomerDTO fromCustomer(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO,Customer.class);
    }
    public List<CustomerDTO> fromListCustomer(List<Customer> customers){
        return customers.stream()
                .map(c->modelMapper.map(c,CustomerDTO.class)).collect(Collectors.toList());
    }
}
