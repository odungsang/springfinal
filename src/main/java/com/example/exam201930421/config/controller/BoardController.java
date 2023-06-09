package com.example.exam201930421.config.controller;

import com.example.exam201930421.config.security.JwtTokenProvider;
import com.example.exam201930421.dto.BoardDto;
import com.example.exam201930421.dto.BoardResponseDto;
import com.example.exam201930421.dto.ChangeBoardDto;
import com.example.exam201930421.service.BoardService;
import com.example.exam201930421.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {


    private final BoardService boardService;

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public BoardController(BoardService boardService, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.boardService = boardService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> boardResponseDtoList = boardService.getAllBoards();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDtoList);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/listOrderByCreatedAt")
    public ResponseEntity<List<BoardResponseDto>> listBoardsByCreatedAtDesc() {
        List<BoardResponseDto> boardResponseDtoList = boardService.listBoardsByCreatedAtDesc();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDtoList);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardResponseDto>> getBoardsByUserId(@RequestParam String userId) {
        List<BoardResponseDto> boardResponseDtoList = boardService.getBoardsByUserName(userId);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDtoList);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public ResponseEntity<BoardResponseDto> getBoardById(Long number) {
        BoardResponseDto boardResponseDto = boardService.getBoardById(number);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN' )or hasRole('USER')")
    @PostMapping
    public ResponseEntity<BoardResponseDto> saveBoard(@RequestBody BoardDto boardDto) {
        BoardResponseDto boardResponseDto = boardService.createBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<BoardResponseDto> changeBoard(HttpServletRequest request, @RequestBody ChangeBoardDto changeBoardDTO) throws Exception{
        BoardResponseDto takenBoard = boardService.getBoard(changeBoardDTO.getId());
        String id = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        if(takenBoard.getUserId().equals(id)) {
            BoardResponseDto boardResponseDto =
                    boardService.updateBoard(id, changeBoardDTO);
            return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<String> deleteBoard(HttpServletRequest request,Long id) throws Exception {
        BoardResponseDto boardResponseDto = boardService.getBoard(id);
        String userId = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        if(boardResponseDto.getUserId().equals(userId)) {
            boardService.deleteBoard(id);
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        }
        return ResponseEntity.status(HttpStatus.OK).body("권한을 확인해주세요");
    }

}
