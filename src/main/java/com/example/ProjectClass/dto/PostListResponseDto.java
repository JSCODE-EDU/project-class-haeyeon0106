package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.Post;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class PostListResponseDto {
    @ApiModelProperty(example = "게시글 번호")
    private Long id;
    @ApiModelProperty(example = "게시글 제목")
    private String title;
    @ApiModelProperty(example = "게시글 내용")
    private String contents;
    @ApiModelProperty(example = "게시글 생성시간")
    private String createdAt;

    public PostListResponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.createdAt = entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
