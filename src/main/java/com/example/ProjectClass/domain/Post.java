package com.example.ProjectClass.domain;

import com.example.ProjectClass.dto.PostUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String contents;


    @Builder
    public Post (Long id,String title, String contents){
        this.id = id;
        this.title = title;
        this.contents = contents;
    }
    public void update(PostUpdateDto updateDto){
        this.title = updateDto.getTitle();
        this.contents = updateDto.getContents();
    }
}
