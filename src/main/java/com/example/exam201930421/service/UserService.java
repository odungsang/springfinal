package com.example.exam201930421.service;

import com.example.exam201930421.dto.UserResponseDto;
import com.example.exam201930421.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> getAllUsersSortedByName();

    UserResponseDto getUserById(String id);
}
