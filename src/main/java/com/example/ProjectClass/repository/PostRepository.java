package com.example.ProjectClass.repository;

import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.dto.PostListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAllByTitleContaining(String keyword, Pageable pageable);

    default Page<PostListResponseDto> findAllByTitleContainingDto(String keyword,Pageable pageable){
        return findAllByTitleContaining(keyword,pageable).map(PostListResponseDto::new);
    }

    default Page<PostListResponseDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(PostListResponseDto::new);
    }
}
