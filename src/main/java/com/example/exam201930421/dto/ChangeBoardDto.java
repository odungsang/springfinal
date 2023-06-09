package com.example.exam201930421.dto;

import com.example.exam201930421.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeBoardDto {
    private Long id;
    private String title;
    private String contents;
}
