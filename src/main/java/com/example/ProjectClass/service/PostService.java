package com.example.ProjectClass.service;

import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.dto.PostDetailResponseDto;
import com.example.ProjectClass.dto.PostListResponseDto;
import com.example.ProjectClass.dto.PostSaveRequestDto;
import com.example.ProjectClass.dto.PostUpdateDto;
import com.example.ProjectClass.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void savePost(PostSaveRequestDto requestDto){
        postRepository.save(requestDto.toEntity());
    }

//    @Transactional
//    public List<PostListResponseDto> getPostAll(){
//        return postRepository.findAll()
//                .stream()
//                .map(PostListResponseDto::new).collect(Collectors.toList());
//    }
//    @Transactional
//    public PostDetailResponseDto getPostDetail(Long postId){
//        Post post = postRepository.findById(postId)
//                .orElseThrow(()->new IllegalArgumentException(""));
//        return new PostDetailResponseDto(post);
//    }

    @Transactional
    public void updatePost(Long postId, PostUpdateDto updateDto){
        Post post  = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
        post.update(updateDto);
    }

    @Transactional
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}
