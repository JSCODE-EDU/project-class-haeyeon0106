package com.example.ProjectClass.repository;

import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.dto.PostListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("select p from Post p order by p.id DESC")
    List<Post> findAllDesc();
}
