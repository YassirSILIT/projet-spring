package com.iSoftNetwork.ordersmservice.web;

import com.iSoftNetwork.ordersmservice.dto.OrderResponseDTO;
import com.iSoftNetwork.ordersmservice.dto.ProductItemsRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.ProductItemsResponseDTO;
import com.iSoftNetwork.ordersmservice.entities.Order;
import com.iSoftNetwork.ordersmservice.entities.ProductItems;
import com.iSoftNetwork.ordersmservice.repository.OrderRepository;
import com.iSoftNetwork.ordersmservice.service.OrderService;
import com.iSoftNetwork.ordersmservice.service.ProductItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductItemController {
    private ProductItemService productItemService;
    private OrderService orderService;
    private OrderRepository orderRepository;

    public ProductItemController(ProductItemService productItemService,
                                 OrderService orderService, OrderRepository orderRepository) {
        this.productItemService = productItemService;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }
    @GetMapping("/productItems")
    public List<ProductItemsResponseDTO> getAllProductItems(){
        return productItemService.getAllProductItems();
    }
    @GetMapping("/allProductItems")
    public List<ProductItems> findAllProductItems(){
        return productItemService.findAllProductItems();
    }
    @GetMapping("/productItems/{id}")
    public ProductItemsResponseDTO getProductItemById(@PathVariable Long id){
        return productItemService.findProductItemsById(id);
    }
    @PostMapping("/productItems/order/{orderId}")
    public ProductItemsResponseDTO addProductItem(@RequestBody ProductItemsRequestDTO productItemsRequestDTO,
                                                  @PathVariable String orderId){
        //Order order = orderRepository.findById(orderId).get();
        Order byId = orderService.findById(orderId);
        return productItemService.saveProductItem(productItemsRequestDTO, byId);
    }
    @PostMapping("/productItems/orders/{orderId}")
    public ProductItems saveProductItem(@RequestBody ProductItems productItems,
                                                  @PathVariable String orderId){
        //Order order = orderRepository.findById(orderId).get();
        Order byId = orderService.findById(orderId);
        return productItemService.addProductItem(productItems,byId);
    }
    @DeleteMapping("/productItems/{id}")
    public void deleteProductItem(@PathVariable Long id){
        productItemService.deleteProductItemById(id);
    }
    @GetMapping("/productItems/product/{productId}")
    public List<ProductItemsResponseDTO> allProductItemByProductId(@PathVariable Long productId){
        return productItemService.productItemsByProductId(productId);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
