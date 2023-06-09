package com.example.exam201930421.service.impl;

import com.example.exam201930421.dao.UserDAO;
import com.example.exam201930421.dto.UserResponseDto;
import com.example.exam201930421.entity.User;
import com.example.exam201930421.repository.UserRepository;
import com.example.exam201930421.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, UserRepository userRepository) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto getUserById(String id) {
        User user = userDAO.selectUserByUid(id);

        UserResponseDto userResponseDTO = new UserResponseDto();
        userResponseDTO.setUid(user.getUid());
        userResponseDTO.setName(user.getName());

        return userResponseDTO;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersSortedByName() {
        return userRepository.findAllByOrderByNameAsc();
    }
}