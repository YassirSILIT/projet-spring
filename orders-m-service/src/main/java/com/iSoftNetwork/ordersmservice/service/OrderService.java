package com.iSoftNetwork.ordersmservice.service;

import com.iSoftNetwork.ordersmservice.dto.OrderRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.OrderResponseDTO;
import com.iSoftNetwork.ordersmservice.entities.Order;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO getOrderById(String id);
    Order findById(String id);
    List<Order> allOrders();
    OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO);
    void deleteOrderById(String id);
    List<OrderResponseDTO> orderByCustomerId(Long customerId);
    OrderResponseDTO updateOrder(OrderRequestDTO orderRequestDTO,String id);
    Order updateOrders(Order order,String id);
}
