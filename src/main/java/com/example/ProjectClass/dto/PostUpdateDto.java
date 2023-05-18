package com.example.ProjectClass.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class PostUpdateDto {
    @NotBlank
    @Size(min = 1,max = 15)
    private String title;
    @NotEmpty
    @Size(min = 1,max = 1000)
    private String contents;
}
