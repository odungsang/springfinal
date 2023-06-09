package com.example.exam201930421.dto;

import com.example.exam201930421.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardResponseDto {
    private long id;
    private String title;
    private String contents;
    private String userId;
    private String userName;
    private Board board;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.userId = board.getUserId();
        this.userName = board.getUserName();
    }

    public BoardResponseDto(long id, String title, String contents, String userId, String userName) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.userName = userName;
    }



}
