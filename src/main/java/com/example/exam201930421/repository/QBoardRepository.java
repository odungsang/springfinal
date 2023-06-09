package com.example.exam201930421.repository;

import com.example.exam201930421.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QBoardRepository
    extends JpaRepository<Board,Long>, QuerydslPredicateExecutor<Board> {
}
