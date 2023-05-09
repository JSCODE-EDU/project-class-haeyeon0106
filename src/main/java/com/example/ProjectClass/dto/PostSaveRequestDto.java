package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String contents;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .contents(contents)
                .build();
    }
}
