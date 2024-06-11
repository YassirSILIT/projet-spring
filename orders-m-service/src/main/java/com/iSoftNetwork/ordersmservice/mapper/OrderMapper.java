package com.iSoftNetwork.ordersmservice.mapper;

import com.iSoftNetwork.ordersmservice.dto.OrderRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.OrderResponseDTO;
import com.iSoftNetwork.ordersmservice.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order fromOrderRequestDTO(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO fromOrder(Order order);
    
}
