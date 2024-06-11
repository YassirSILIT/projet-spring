package com.iSoftNetwork.inventoryservice;

import com.iSoftNetwork.inventoryservice.entities.Category;
import com.iSoftNetwork.inventoryservice.entities.Product;
import com.iSoftNetwork.inventoryservice.repository.CategoryRepository;
import com.iSoftNetwork.inventoryservice.repository.ProductRepository;
import com.iSoftNetwork.inventoryservice.services.ProductService;
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
				title = "Inventory-Service",
				version = "1.0.0",
				description = "This project is only for Inventory MicroServices",
				contact = @Contact(
						name = "Mr Yassir",
						email = "yassir.silit@outlook.com"
				)
		)
)
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	//@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository,
										CategoryRepository categoryRepository){
		return args -> {
			productRepository.save(Product.builder()
					.name("Pc HP").priceHT(12345).quantity(4).priceTtc(1345565).tva(15).build());
			productRepository.save(Product.builder()
					.name("Iphone 15").priceHT(13345).quantity(5).priceTtc(345565).tva(10).build());
			productRepository.save(Product.builder()
					.name("Desk Noire").priceHT(5345).quantity(13).priceTtc(145565).tva(20).build());

			categoryRepository.save(Category.builder()
							.code("C01").designation("Laptop").build());
			categoryRepository.save(Category.builder()
					.code("C02").designation("Smart Phone").build());
			categoryRepository.save(Category.builder()
					.code("C03").designation("Gaming Desk").build());
		};
	}

}
