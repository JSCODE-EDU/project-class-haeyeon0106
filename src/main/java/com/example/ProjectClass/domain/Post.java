package com.example.ProjectClass.domain;

import com.example.ProjectClass.dto.PostUpdateDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Lob
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(Long id,String title, String contents,User user){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void update(PostUpdateDto updateDto){
        this.title = updateDto.getTitle();
        this.contents = updateDto.getContents();
    }
}
