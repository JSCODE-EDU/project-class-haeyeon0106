package com.example.ProjectClass.service;

import com.example.ProjectClass.config.jwt.JwtProvider;
import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.domain.User;
import com.example.ProjectClass.dto.CommentRequestDto;
import com.example.ProjectClass.repository.CommentRepository;
import com.example.ProjectClass.repository.PostRepository;
import com.example.ProjectClass.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void saveComment(String token, Long postId, CommentRequestDto requestDto){
        Long userId = Long.valueOf(String.valueOf(jwtProvider.getUid(token)));
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException("일치하는 회원이 없습니다"));
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        commentRepository.save(requestDto.toEntity(user,post));
    }

}
