package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String password;

    public UserResponseDto(User entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }
}
