package com.ensak.customerMservice.service;

import com.ensak.customerMservice.dto.CustomerDTO;
import com.ensak.customerMservice.exception.CustomerNotFoundException;
import com.ensak.customerMservice.exception.EmailAlreadyExistException;

import java.util.List;

public interface CustomerService1 {

    CustomerDTO addCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistException;
    CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException;
    List<CustomerDTO> getAllCustomers();
    List<CustomerDTO> searchCustomers(String keyword);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException;
    void deleteCustomer(Long id) throws CustomerNotFoundException;
}
