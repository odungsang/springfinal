package com.example.exam201930421.repository;


import com.example.exam201930421.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository
        extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product> {
}
