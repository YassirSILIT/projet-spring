package com.iSoftNetwork.ordersmservice.restClient;

import com.iSoftNetwork.ordersmservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="CUSTOMER-M-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path = "/api/v1/customers")
    @CircuitBreaker(name = "customerservice" , fallbackMethod = "allCustomers")
    List<Customer> findAllCustomers();
    @GetMapping(path = "/api/v1/customers/{id}")
    @CircuitBreaker(name = "customerservice" , fallbackMethod = "getCustomer")
    Customer findCustomerById(@PathVariable Long id);

    default Customer getCustomer(Long id, Exception e){
        Customer customer = new Customer();
        customer.getId();
        customer.setFirstName("Not available");
        customer.setLastName("Not available");
        customer.setEmail("Not available");
        customer.setPhone("Not available");
        return customer;
    }
    default List<Customer> allCustomers(Exception e){
        return List.of();
    }
}
