package com.iSoftNetwork.ordersmservice.repository;

import com.iSoftNetwork.ordersmservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String > {
    List<Order> findByCustomerId(Long customerId);
}
