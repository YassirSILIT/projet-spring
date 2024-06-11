package com.iSoftNetwork.ordersmservice.restClient;

import com.iSoftNetwork.ordersmservice.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INVENTORY-SERVICE" )
public interface ProductRestClient {
    @GetMapping(path = "/api/v1/products")
    @CircuitBreaker(name = "inventoryservice", fallbackMethod = "allProducts")
    List<Product> findAllProducts();
    @GetMapping(path = "/api/v1/products/{id}")
    @CircuitBreaker(name = "inventoryservice", fallbackMethod = "getProduct")
    Product findProductById(@PathVariable Long id);

    default List<Product> allProducts(Exception e){
        return List.of();
    }

    default Product getProduct(Long id, Exception e){
        Product product = new Product();
        product.setId(id);
        product.setName("Not available");
        product.setTva(0);
        product.setQuantity(0);
        product.setPriceHT(0);
        product.setPriceTtc(0);
        return product;
    }
}
