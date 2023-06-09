package com.example.exam201930421.dto;

public class ChangeProductDto {

    private  Long number;

    private String name;

    private int price;

    private int stock;

    public ChangeProductDto(Long number, String name) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }
}
