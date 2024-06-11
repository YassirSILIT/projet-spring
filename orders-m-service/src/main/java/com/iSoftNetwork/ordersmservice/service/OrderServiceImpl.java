package com.iSoftNetwork.ordersmservice.service;

import com.iSoftNetwork.ordersmservice.dto.OrderRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.OrderResponseDTO;
import com.iSoftNetwork.ordersmservice.entities.Order;
import com.iSoftNetwork.ordersmservice.exceptions.CustomerNotFoundException;
import com.iSoftNetwork.ordersmservice.exceptions.OrderNotFoundException;
import com.iSoftNetwork.ordersmservice.mapper.OrderMapper;
import com.iSoftNetwork.ordersmservice.model.Customer;
import com.iSoftNetwork.ordersmservice.model.Product;
import com.iSoftNetwork.ordersmservice.repository.OrderRepository;
import com.iSoftNetwork.ordersmservice.restClient.CustomerRestClient;
import com.iSoftNetwork.ordersmservice.restClient.ProductRestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    private OrderMapper orderMapper;
    private OrderRepository orderRepository;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;

    public OrderServiceImpl(OrderMapper orderMapper, OrderRepository orderRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(order -> {
            Customer customer = customerRestClient.findCustomerById(order.getCustomerId());
            order.setCustomer(customer);
        });
        return allOrders.stream().
                map(order -> orderMapper.fromOrder(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getOrderById(String id) {
       Order order = orderRepository.findById(id).orElseThrow(() ->
                (new OrderNotFoundException("Order " + id + " Not Found")));
        /*order.getProductItems().forEach(pr->{
            pr.setProduct(productRestClient.findProductById(pr.getProductId()));
        });*/
        Customer customer = customerRestClient.findCustomerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            pi.setProduct(productRestClient.findProductById(pi.getProductId()));
        });
        return orderMapper.fromOrder(order);
    }

    @Override
    public Order findById(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                (new OrderNotFoundException("Order " + id + " Not Found")));
        Customer customer = customerRestClient.findCustomerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            Product product = productRestClient.findProductById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }

    @Override
    public List<Order> allOrders() {
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(order -> {
            Customer customer = customerRestClient.findCustomerById(order.getCustomerId());
            order.setCustomer(customer);
            /*order.getProductItems().forEach(pi->{
                pi.setProduct(productRestClient.findProductById(pi.getProductId()));
            });*/
        });
        return allOrders;
    }

    @Override
    public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO) {
        log.info(String.format("Saving new order => %s ", orderRequestDTO.toString()));
        Customer customer = null;
        try {
            customer = customerRestClient.findCustomerById(orderRequestDTO.getCustomerId());
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer " +orderRequestDTO.getCustomerId() + " not found");
        }
        Order order = orderMapper.fromOrderRequestDTO(orderRequestDTO);
        order.setId(UUID.randomUUID().toString());
        order.setDate(LocalDate.now());
        Order savedOrder = orderRepository.save(order);
        savedOrder.setCustomer(customer);
        return orderMapper.fromOrder(order);
    }

    @Override
    public void deleteOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty())
            throw new OrderNotFoundException("Order " + id + " Not Found");
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponseDTO> orderByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        try{
            orders.forEach(or -> {
                Customer customer = customerRestClient.findCustomerById(or.getCustomerId());
                or.setCustomer(customer);
            });
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer " + customerId + " not found");
        }
        return orders.stream()
                .map(order -> orderMapper.fromOrder(order))
                .collect(Collectors.toList());
    }
    @Override
    public OrderResponseDTO updateOrder(OrderRequestDTO orderRequestDTO, String id) {
        log.info(String.format("Updating the Order => %s ," +orderRequestDTO.toString()));
        Optional<Order> orderById = orderRepository.findById(id);
        if(orderById.isEmpty()) throw new OrderNotFoundException("Order " +id+ "not found");
        orderRequestDTO.setId(id);
        Order order = orderMapper.fromOrderRequestDTO(orderRequestDTO);
        order.setDate(LocalDate.now());
        Order savedOrder = orderRepository.save(order);
        return orderMapper.fromOrder(savedOrder);
    }

    @Override
    public Order updateOrders(Order order, String id) {
        log.info(String.format("Updating the Order => %s ," +order.toString()));
        Optional<Order> orderById = orderRepository.findById(id);
        if(orderById.isEmpty()) throw new OrderNotFoundException("Order " +id+ "not found");
        order.setId(id);
        order.setDate(LocalDate.now());
        return orderRepository.save(order);
    }


}
