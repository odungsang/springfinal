package com.example.exam201930421.dto;


import com.example.exam201930421.entity.User;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;

    private String uid;

    private String name;

    private String email;


    public UserResponseDto(User user) {
        this.id = user.getId();
        this.uid = user.getUid();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
