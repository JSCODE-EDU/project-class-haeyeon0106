package com.example.ProjectClass.repository;

import com.example.ProjectClass.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {
}
