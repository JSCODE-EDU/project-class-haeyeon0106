package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String createdAt;

    public PostDetailResponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.createdAt = entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
