package com.example.exam201930421.dto;

import com.example.exam201930421.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDto {
    private Long number;
    private String name;
    private int price;
    private int stock;
    private Product product;

    public ProductResponseDto(Product product) {
        this.number = product.getNumber();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }
}
