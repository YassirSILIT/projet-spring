package com.iSoftNetwork.ordersmservice.web;

import com.iSoftNetwork.ordersmservice.dto.OrderRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.OrderResponseDTO;
import com.iSoftNetwork.ordersmservice.entities.Order;
import com.iSoftNetwork.ordersmservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/orders")
    public List<OrderResponseDTO> allOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/order")
    public List<Order> getAllOrders(){
        return orderService.allOrders();
    }
    @GetMapping("/orders/{id}")
    public OrderResponseDTO getOrderById(@PathVariable String id){
        return orderService.getOrderById(id);
    }
    @GetMapping("/order/{id}")
    public Order findOrderById(@PathVariable String id){
        return orderService.findById(id);
    }
    @GetMapping("/orders/customers/{customerId}")
    public List<OrderResponseDTO> allOrdersByCustomerId(@PathVariable Long customerId){
        return orderService.orderByCustomerId(customerId);
    }
    @PostMapping("/orders")
    public OrderResponseDTO saveOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.saveOrder(orderRequestDTO);
    }
    @PutMapping("/updateOrder/{id}")
    public OrderResponseDTO updateOrder(@RequestBody OrderRequestDTO orderRequestDTO,
                                        @PathVariable String id){
        return orderService.updateOrder(orderRequestDTO,id);
    }
    @PutMapping("/updateOrders/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable String id){
        return orderService.updateOrders(order,id);
    }
    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.deleteOrderById(id);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
