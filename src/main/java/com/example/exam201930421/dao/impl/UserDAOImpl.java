package com.example.exam201930421.dao.impl;


import com.example.exam201930421.dao.UserDAO;
import com.example.exam201930421.entity.User;
import com.example.exam201930421.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;
    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository =userRepository;
    }
    @Override
    public List<User> selectAllByOrderByPriceAsc() {
        return userRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<User> selectAllBy() {
        return userRepository.findAllBy();
    }

    @Override
    public User selectUserByUid(String uid) {
        return userRepository.getByUid(uid);
    }
}
