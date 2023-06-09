package com.example.exam201930421.dao;



import com.example.exam201930421.entity.Product;

import java.util.List;

public interface ProductDAO {

    Product selectProduct(Long id);

    Product insertProduct(Product product);

    Product updateUserNamePriceStock(Long number, String name, int price, int stock) throws Exception;

    Product updateProductStock(Long number, int stock) throws Exception;

    void deleteProduct(Long number) throws Exception;

    List<Product> selectAllByOrderByPriceDESC();

    List<Product> selectAllBy();

    List<Product> selectProductByName(String name);

    List<Product> selectProductByNumber(Long number);

}
