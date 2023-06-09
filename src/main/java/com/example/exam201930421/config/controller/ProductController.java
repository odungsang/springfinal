package com.example.exam201930421.config.controller;

import com.example.exam201930421.dto.ChangeProductDto;
import com.example.exam201930421.dto.ProductDto;
import com.example.exam201930421.dto.ProductResponseDto;
import com.example.exam201930421.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> productResponseDtoList = productService.allProduct();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/listOrderByPrice")
    public ResponseEntity<List<ProductResponseDto>> listProductsByPriceDesc() {
        List<ProductResponseDto> productResponseDtoList = productService.allProductOrderByPriceDESC();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDto>> getProductsByName(@RequestParam String name) {
        List<ProductResponseDto> productResponseDtoList = productService.getProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public ResponseEntity<ProductResponseDto> getProductById(Long number) {
        ProductResponseDto productResponseDto = productService.getOneProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ChangeProductDto changeProductDto) throws Exception{
        ProductResponseDto productResponseDto = productService.changeProductNamePriceStock(changeProductDto.getNumber(), changeProductDto.getName(), changeProductDto.getPrice(), changeProductDto.getStock());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception{
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}