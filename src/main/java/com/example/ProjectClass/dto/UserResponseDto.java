package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private LocalDateTime created_at;

    public UserResponseDto(User entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.created_at = entity.getCreatedAt();
    }
}
