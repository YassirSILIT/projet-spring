package com.iSoftNetwork.ordersmservice;

import com.iSoftNetwork.ordersmservice.entities.Order;
import com.iSoftNetwork.ordersmservice.entities.OrderState;
import com.iSoftNetwork.ordersmservice.entities.ProductItems;
import com.iSoftNetwork.ordersmservice.model.Customer;
import com.iSoftNetwork.ordersmservice.model.Product;
import com.iSoftNetwork.ordersmservice.repository.OrderRepository;
import com.iSoftNetwork.ordersmservice.repository.ProductItemRepository;
import com.iSoftNetwork.ordersmservice.restClient.CustomerRestClient;
import com.iSoftNetwork.ordersmservice.restClient.ProductRestClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Order-Service",
				version = "1.0.0",
				description = "This project is only for Order MicroServices",
				contact = @Contact(
						name = "Mr Yassir",
						email = "yassir.silit@outlook.com"
				)
		)
)
public class OrdersMServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersMServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRestClient productRestClient,
										ProductItemRepository productItemRepository,
										OrderRepository orderRepository,
										CustomerRestClient customerRestClient) {
		return args -> {
			List<Product> allProducts = productRestClient.findAllProducts();
			List<Customer> allCustomers = customerRestClient.findAllCustomers();
			allProducts.forEach(product -> {
				System.out.println(product.toString());
			});
			allCustomers.forEach(customer -> {
				System.out.println(customer.toString());
			});

			/*for (int i = 0; i < 1; i++) {
				Customer customer = null;
				Order order = Order.builder()
						.id(UUID.randomUUID().toString())
						.state(OrderState.CONFIRMED)
						.date(LocalDate.now())
						.customerId(1L)
						.build();
				Order savedOrder = orderRepository.save(order);

				allProducts.forEach(product -> {
					ProductItems productItems = ProductItems.builder()
							.productId(product.getId())
							.quantity(product.getQuantity())
							.order(savedOrder)
							.build();
					productItemRepository.save(productItems);
				});
			}*/
		};
	}
}