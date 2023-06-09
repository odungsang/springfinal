package com.example.exam201930421.service;



import com.example.exam201930421.dto.ProductDto;
import com.example.exam201930421.dto.ProductResponseDto;
import com.example.exam201930421.entity.Product;

import java.util.List;

public interface ProductService {

    ProductResponseDto getOneProduct(Long id);
    ProductResponseDto createProduct(ProductDto productDTO);

    ProductResponseDto changeProductNamePriceStock(Long number, String name, int price, int stock) throws Exception;

    ProductResponseDto changeProductStock(Long number, int stock) throws Exception;
    void deleteProduct(Long number) throws Exception;

    List<ProductResponseDto> allProductOrderByPriceDESC();

    List<ProductResponseDto> allProduct();

    List<ProductResponseDto> getProductByName(String name);

    List<ProductResponseDto> getProductByNumber(Long number);
}
