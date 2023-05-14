package com.example.ProjectClass.service;

import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.dto.PostListResponseDto;
import com.example.ProjectClass.dto.PostResponseDto;
import com.example.ProjectClass.dto.PostSaveRequestDto;
import com.example.ProjectClass.dto.PostUpdateDto;
import com.example.ProjectClass.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void savePost(PostSaveRequestDto requestDto){
        postRepository.save(requestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public Page<PostListResponseDto> getPostAll(Pageable pageable){
        return postRepository.findAllDto(pageable);
//                .stream()
//                .map(PostListResponseDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public PostResponseDto getPostDetail(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return new PostResponseDto(post);
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateDto updateDto){
        Post post  = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
        post.update(updateDto);
    }

    @Transactional
    public void deletePost(Long postId){
        Post post  = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
        postRepository.deleteById(postId);
    }

    @Transactional
    public Page<PostListResponseDto> searchPost(String keyword, Pageable pageable){
        return postRepository.findAllByTitleContainingDto(keyword, pageable);
    }
}
