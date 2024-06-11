package com.ensak.customerMservice.web;

import com.ensak.customerMservice.dto.CustomerDTO;
import com.ensak.customerMservice.service.CustomerService1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestController1 {
    private CustomerService1 customerService;

    public CustomerRestController1(CustomerService1 customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }
    @GetMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id , @RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(id,customerDTO);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam String keyword){
        return customerService.searchCustomers(keyword);
    }
    //http://localhost:8082/customers/search?keyword=yassir
    /*@GetMapping("/auth")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
