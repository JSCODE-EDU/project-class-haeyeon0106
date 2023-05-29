package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {
    private String contents;
    private LocalDateTime createdAt;
    private String email;

    public CommentResponseDto(Comment comment){
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.email = comment.getUser().getEmail();
    }
}
