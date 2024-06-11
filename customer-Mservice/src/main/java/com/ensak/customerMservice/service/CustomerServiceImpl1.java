package com.ensak.customerMservice.service;

import com.ensak.customerMservice.dto.CustomerDTO;
import com.ensak.customerMservice.entities.Customer;
import com.ensak.customerMservice.exception.CustomerNotFoundException;
import com.ensak.customerMservice.exception.EmailAlreadyExistException;
import com.ensak.customerMservice.mapper.CustomerMapper1;
import com.ensak.customerMservice.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CustomerServiceImpl1 implements CustomerService1{

    private CustomerMapper1 customerMapper;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl1(CustomerMapper1 customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistException {
        log.info(String.format("Saving new customer => %s", customerDTO.toString()));
        Optional<Customer> byEmail = customerRepository.findByEmail(customerDTO.getEmail());
        if (byEmail.isPresent()) {
            log.error(String.format("This email %s already exist",customerDTO.getEmail()));
            throw new EmailAlreadyExistException();
        }
        Customer customerToSave = customerMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customerToSave);
        CustomerDTO result = customerMapper.fromCustomer(savedCustomer);
        return result;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new CustomerNotFoundException("");
        return customerMapper.fromCustomer(customer.get());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return customerMapper.fromListCustomer(allCustomers);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.findByFirstNameContainsIgnoreCase(keyword);
        if (customers.isEmpty()) throw new CustomerNotFoundException("");
        return customerMapper.fromListCustomer(customers);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.info(String.format("Updating the Customer => %s ", customerDTO.toString()));
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new CustomerNotFoundException("");
        customerDTO.setId(id); // to see
        Customer customerToUpdate = customerMapper.fromCustomerDTO(customerDTO);
        Customer updateCustomer = customerRepository.save(customerToUpdate);
        return customerMapper.fromCustomer(updateCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()) throw new CustomerNotFoundException("");
        customerRepository.deleteById(id);
    }
}
