package com.example.exam201930421.config.controller;

import com.example.exam201930421.entity.User;
import com.example.exam201930421.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUserList() {
        return userService.getAllUsers();
    }

    @GetMapping("/listOrderByName")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUserListOrderByName() {
        return userService.getAllUsersSortedByName();
    }

}
