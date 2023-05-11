package com.example.ProjectClass.domain;

import com.example.ProjectClass.dto.PostUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;

    public void update(PostUpdateDto updateDto){
        this.title = updateDto.getTitle();
        this.contents = updateDto.getContents();
    }
}
