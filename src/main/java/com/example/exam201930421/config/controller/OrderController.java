package com.example.exam201930421.config.controller;

import com.example.exam201930421.config.security.JwtTokenProvider;
import com.example.exam201930421.dto.*;
import com.example.exam201930421.dto.ProductResponseDto;
import com.example.exam201930421.dto.UserResponseDto;
import com.example.exam201930421.service.OrderService;
import com.example.exam201930421.service.ProductService;
import com.example.exam201930421.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public OrderController(OrderService orderService, JwtTokenProvider jwtTokenProvider, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping()
    public ResponseEntity<OrderResponseDTO> createOrder(HttpServletRequest request, @RequestParam Long productId,
        @RequestParam String productName) throws Exception {
        String id = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        UserResponseDto userResponseDTO = userService.getUserById(id);
        ProductResponseDto productResponseDTO = productService.getOneProduct(productId);
        if(productResponseDTO.getStock() == 0) {
            return null;
        } else {
            OrderDTO newOrder = new OrderDTO();
            newOrder.setProductId(productId);
            newOrder.setProductName(productName);
            newOrder.setUserId(userResponseDTO.getUid());
            newOrder.setUserName(userResponseDTO.getName());
            newOrder.setPrice(productResponseDTO.getPrice());
            OrderResponseDTO orderResponseDTO = orderService.createOrder(newOrder);
            productService.changeProductStock(productResponseDTO.getNumber(), productResponseDTO.getStock());

            return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrder() {
        List<OrderResponseDTO> orderResponseDTO = orderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/listByUserId")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrderListByUserId(String userId) {
        List<OrderResponseDTO> orderResponseDTO = orderService.getAllOrderListByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/listByProductId")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrderListByProductId(Long productId) {
        List<OrderResponseDTO> orderResponseDTO = orderService.getAllOrderListByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<OrderResponseDTO> getOrderById(long id) {
        OrderResponseDTO orderResponseDTO = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
}
