package com.example.exam201930421.dao;



import com.example.exam201930421.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> selectAllByOrderByPriceAsc();
    List<User> selectAllBy();

    User selectUserByUid(String uid);
}
