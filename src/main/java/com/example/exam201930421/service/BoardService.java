package com.example.exam201930421.service;

import com.example.exam201930421.dto.BoardDto;
import com.example.exam201930421.dto.BoardResponseDto;
import com.example.exam201930421.dto.ChangeBoardDto;
import com.example.exam201930421.dto.ProductResponseDto;

import java.util.List;

public interface BoardService {



    BoardResponseDto getBoard(Long id);

    BoardResponseDto createBoard(BoardDto boardDto);

    BoardResponseDto updateBoard(String id, ChangeBoardDto changeBoardDto) throws Exception;

    void deleteBoard(Long id) throws Exception;

    List<BoardResponseDto> getAllBoards();

    List<BoardResponseDto> getBoardsByUserName(String userName);

    List<BoardResponseDto> listBoardsByCreatedAtDesc();


    BoardResponseDto getBoardById(Long id);
}
