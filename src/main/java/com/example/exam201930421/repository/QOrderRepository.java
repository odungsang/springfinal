package com.example.exam201930421.repository;

import com.example.exam201930421.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QOrderRepository extends JpaRepository<Order,Long>, QuerydslPredicateExecutor<Order> {
}
