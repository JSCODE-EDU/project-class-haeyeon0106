package com.example.ProjectClass.repository;

import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.domain.PostLike;
import com.example.ProjectClass.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    PostLike findByPostAndUser(Post post, User user);
}
