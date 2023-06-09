package com.example.exam201930421.service.impl;

import com.example.exam201930421.dao.BoardDAO;
import com.example.exam201930421.dto.BoardDto;
import com.example.exam201930421.dto.BoardResponseDto;
import com.example.exam201930421.dto.ChangeBoardDto;
import com.example.exam201930421.entity.Board;
import com.example.exam201930421.service.BoardService;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public BoardResponseDto createBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setUserId(boardDto.getUser_id());
        board.setUserName(boardDto.getUser_name());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        Board saveBoard = boardDAO.insertBoard(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(saveBoard.getId());
        boardResponseDto.setTitle(saveBoard.getTitle());
        boardResponseDto.setContents(saveBoard.getContents());
        boardResponseDto.setUserId(saveBoard.getUserId());
        boardResponseDto.setUserName(saveBoard.getUserName());

        return boardResponseDto;
    }


    @Override
    public BoardResponseDto getBoard(Long id) {
        Board board = boardDAO.selectBoard(id);
        BoardResponseDto boardResponseDTO =
                new BoardResponseDto(board.getId(), board.getTitle(), board.getContents(), board.getUserId(), board.getUserName());
        return boardResponseDTO;
    }

    @Override
    public BoardResponseDto updateBoard(String id, ChangeBoardDto changeBoardDto) throws Exception {
        Board changeBoard = boardDAO.updateBoard(id, changeBoardDto);
        return new BoardResponseDto(changeBoard);
    }

    @Override
    public void deleteBoard(Long id) throws Exception {
        boardDAO.deleteBoard(id);
    }

    @Override
    public List<BoardResponseDto> getAllBoards() {
        List<Board> boards = boardDAO.allBoard();
        List<BoardResponseDto> boardResponseDtoList =
                boards.stream()
                        .map(board -> new BoardResponseDto(board))
                        .collect(Collectors.toList());
        return boardResponseDtoList;
    }

    @Override
    public List<BoardResponseDto> getBoardsByUserName(String user_name) {
        List<Board> boards = boardDAO.selectBoardByUserName(user_name);
        return boards.stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardResponseDto> listBoardsByCreatedAtDesc() {
        List<Board> boards = boardDAO.listBoardByCreatedAtDesc();
        return boards.stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }


    @Override
    public BoardResponseDto getBoardById(Long id) {
        Board board = boardDAO.selectBoard(id);
        return new BoardResponseDto(board);
    }
}
