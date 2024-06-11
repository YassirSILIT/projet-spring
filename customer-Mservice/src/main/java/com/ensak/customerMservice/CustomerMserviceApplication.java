package com.ensak.customerMservice;

import com.ensak.customerMservice.entities.Customer;
import com.ensak.customerMservice.repositories.CustomerRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Customer-Service",
				version = "1.0.0",
				description = "This project is only for Customer MicroServices",
				contact = @Contact(
						name = "Mr Yassir",
						email = "yassir.silit@outlook.com"
				)
		)
)
public class CustomerMserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMserviceApplication.class, args);
	}
	//@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder().
					firstName("Yassir").lastName("SILIT").email("yassir@gmail.com").phone("0622572313").build());
			customerRepository.save(Customer.builder().
					firstName("Ahmed").lastName("SOUNA").email("ahmed@gmail.com").phone("0693844682").build());
			customerRepository.save(Customer.builder().
					firstName("Lomrani").lastName("SILIT").email("lomrani@gmail.com").phone("0661790083").build());
		};
	}
}
