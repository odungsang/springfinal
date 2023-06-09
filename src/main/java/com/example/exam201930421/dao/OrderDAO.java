package com.example.exam201930421.dao;

import com.example.exam201930421.entity.Order;

import java.util.List;

public interface OrderDAO {
    Order insertOrder(Order order);
    Order selectOrder(long Id);
    List<Order> selectAllBy();
    List<Order> selectAllByUserId(String userId);
    List<Order> selectAllByProductId(Long productId);
}
