package com.example.exam201930421.repository;

import com.example.exam201930421.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);
    List<User> findAllByOrderByNameAsc();
    List<User> findAllBy();
}
