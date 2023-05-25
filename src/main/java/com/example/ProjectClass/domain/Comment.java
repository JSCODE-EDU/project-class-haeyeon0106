package com.example.ProjectClass.domain;

import com.example.ProjectClass.dto.CommentRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(Long id, String contents, User user, Post post){
        this.id = id;
        this.contents = contents;
        this.user = user;
        this.post = post;
    }

    public void update(CommentRequestDto requestDto){
        this.contents = requestDto.getContents();
    }
}
