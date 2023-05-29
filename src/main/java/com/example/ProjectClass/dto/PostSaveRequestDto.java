package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    @NotBlank(message = "제목은 필수입니다.")
    @Size(min = 1, max = 15)
    private String title;

    @NotEmpty(message = "내용은 필수입니다.")
    @Size(min = 1, max = 1000)
    private String contents;

    private User user;

    public Post toEntity(User user){
        return Post.builder()
                .title(title)
                .contents(contents)
                .user(user)
                .build();
    }
}
