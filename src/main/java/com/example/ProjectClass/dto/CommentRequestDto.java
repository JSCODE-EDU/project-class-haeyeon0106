package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.Comment;
import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentRequestDto {

    private String contents;

    private User user;
    private Post post;

    public Comment toEntity(User user, Post post){
        return Comment.builder()
                .contents(contents)
                .user(user)
                .post(post)
                .build();
    }
}
