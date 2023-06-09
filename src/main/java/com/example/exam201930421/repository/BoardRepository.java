package com.example.exam201930421.repository;

import com.example.exam201930421.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();

    List<Board> findByUserName(String userName, Sort sort);

    List<Board> findAllBy();

    List<Board> findAllByUserId(String uid);

    List<Board> findAllById(Long id);



}
