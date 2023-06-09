package com.example.exam201930421.dao.impl;

import com.example.exam201930421.dao.BoardDAO;
import com.example.exam201930421.dto.ChangeBoardDto;
import com.example.exam201930421.entity.Board;
import com.example.exam201930421.repository.BoardRepository;
import com.example.exam201930421.repository.QBoardRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.exam201930421.entity.QBoard.board;

@Component
public class BoardDAOImpl implements BoardDAO {

    private final BoardRepository boardRepository;
    private final QBoardRepository qBoardRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public BoardDAOImpl(BoardRepository boardRepository, QBoardRepository qBoardRepository, JPAQueryFactory jpaQueryFactory) {
        this.boardRepository = boardRepository;
        this.qBoardRepository = qBoardRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public Board insertBoard(Board board) {
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        return boardRepository.save(board);
    }

    @Override
    public Board selectBoard(Long id) {
        Predicate predicate = board.id.eq(id);
        Optional<Board> selectBoard = qBoardRepository.findOne(predicate);
        return selectBoard.orElse(null);
    }

    @Override
    public Board updateBoard(String id, ChangeBoardDto changeBoardDto) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(changeBoardDto.getId());

        Board updateBoard;
        if(selectedBoard.isPresent()) {
            Board board = selectedBoard.get();
            board.setTitle(changeBoardDto.getTitle());
            board.setContents(changeBoardDto.getContents());
            board.setUpdatedAt(LocalDateTime.now());
            updateBoard = boardRepository.save(board);
        } else throw new Exception();
        return updateBoard;
    }

    @Override
    public List<Board> selectAllByUid(String uid) {
        return boardRepository.findAllByUserId(uid);
    }

    @Override
    public List<Board> selectAllById(Long id) {
        return boardRepository.findAllById(id);
    }


    @Override
    public void deleteBoard(Long id) throws Exception {
        Optional<Board> selectedProduct = boardRepository.findById(id);
        if(selectedProduct.isPresent()) {
            Board board = selectedProduct.get();
            boardRepository.delete(board);
        } else throw new Exception();

    }

    @Override
    public List<Board> selectBoardByUserName(String user_name) {
        return boardRepository.findByUserName(user_name, Sort.by(Sort.Order.asc("id")));
    }

    @Override
    public List<Board> listBoardByCreatedAtDesc() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Board> allBoard() {
        return jpaQueryFactory.selectFrom(board)
                .where(board.id.isNotNull())
                .orderBy(board.id.desc())
                .fetch();
    }
}
