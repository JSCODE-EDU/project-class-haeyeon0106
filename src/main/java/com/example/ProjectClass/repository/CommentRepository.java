package com.example.ProjectClass.repository;

import com.example.ProjectClass.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
