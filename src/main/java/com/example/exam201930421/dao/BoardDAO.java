package com.example.exam201930421.dao;

import com.example.exam201930421.dto.ChangeBoardDto;
import com.example.exam201930421.entity.Board;

import java.util.List;

public interface BoardDAO {
    Board insertBoard(Board board);

    Board selectBoard(Long id);


    Board updateBoard(String id, ChangeBoardDto changeBoardDto) throws Exception;


    void deleteBoard(Long id) throws Exception;

    List<Board> selectBoardByUserName(String user_name);

    List<Board> listBoardByCreatedAtDesc();

    List<Board> allBoard();

    List<Board> selectAllByUid(String uid);

    List<Board> selectAllById(Long id);
}
