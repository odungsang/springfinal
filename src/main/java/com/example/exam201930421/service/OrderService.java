package com.example.exam201930421.service;

import com.example.exam201930421.dto.OrderDTO;
import com.example.exam201930421.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderDTO orderDTO);
    List<OrderResponseDTO> getAllOrder();
    List<OrderResponseDTO> getAllOrderListByUserId(String userId);
    List<OrderResponseDTO> getAllOrderListByProductId(Long productId);
    OrderResponseDTO getOrderById(long Id);
}
