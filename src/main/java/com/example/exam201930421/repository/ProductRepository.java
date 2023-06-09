package com.example.exam201930421.repository;


import com.example.exam201930421.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllBy();

    List<Product> findByName(String name);
    List<Product> findByNumber(Long number);

    @Query("SELECT p FROM  Product AS p where p.stock = :stock")
    List<Product> listByStock(@Param("stock")int stock);

}
