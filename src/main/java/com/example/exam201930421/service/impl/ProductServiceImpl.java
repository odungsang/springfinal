package com.example.exam201930421.service.impl;


import com.example.exam201930421.dao.ProductDAO;
import com.example.exam201930421.dto.ProductDto;
import com.example.exam201930421.dto.ProductResponseDto;
import com.example.exam201930421.entity.Product;
import com.example.exam201930421.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }



    @Override
    public ProductResponseDto getOneProduct(Long id) {
        Product product = productDAO.selectProduct(id);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(saveProduct.getNumber());
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductNamePriceStock(Long number, String name, int price, int stock) throws Exception {
        Product changeproduct = productDAO.updateUserNamePriceStock(number, name, price, stock);
        return new ProductResponseDto(changeproduct);
    }

    @Override
    public ProductResponseDto changeProductStock(Long number, int stock) throws Exception {
        Product changeproduct = productDAO.updateProductStock(number, stock);
        return new ProductResponseDto(changeproduct);
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }

    @Override
    public List<ProductResponseDto> allProductOrderByPriceDESC() {
        List<Product> products = productDAO.selectAllByOrderByPriceDESC();
        List<ProductResponseDto> productResponseDto = products.stream().map(item ->
                new ProductResponseDto(item)).collect(Collectors.toList());
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> allProduct() {
        List<Product> products = productDAO.selectAllBy();
        List<ProductResponseDto> productResponseDto = products.stream().map(item ->
                new ProductResponseDto(item)).collect(Collectors.toList());
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getProductByName(String name) {
        List<Product> product = productDAO.selectProductByName(name);
        List<ProductResponseDto> productResponseDto = product.stream().map(item ->
                new ProductResponseDto(item)).collect(Collectors.toList());
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getProductByNumber(Long number) {
        List<Product> product = productDAO.selectProductByNumber(number);
        List<ProductResponseDto> productResponseDto = product.stream().map(item ->
                new ProductResponseDto(item)).collect(Collectors.toList());
        return productResponseDto;
    }
}