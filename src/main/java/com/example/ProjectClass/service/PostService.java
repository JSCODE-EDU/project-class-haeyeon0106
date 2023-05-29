package com.example.ProjectClass.service;

import com.example.ProjectClass.config.jwt.JwtProvider;
import com.example.ProjectClass.domain.Post;
import com.example.ProjectClass.domain.PostLike;
import com.example.ProjectClass.domain.User;
import com.example.ProjectClass.dto.PostListResponseDto;
import com.example.ProjectClass.dto.PostResponseDto;
import com.example.ProjectClass.dto.PostSaveRequestDto;
import com.example.ProjectClass.dto.PostUpdateDto;
import com.example.ProjectClass.repository.PostLikeRepository;
import com.example.ProjectClass.repository.PostRepository;
import com.example.ProjectClass.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    private final JwtProvider jwtProvider;
    @Transactional
    public void savePost(String token,PostSaveRequestDto requestDto){
        Long userId = Long.valueOf(String.valueOf(jwtProvider.getUid(token)));
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException("일치하는 회원이 없습니다"));
        postRepository.save(requestDto.toEntity(user));
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
    public void updatePost(String token, Long postId,PostUpdateDto updateDto){
        Long userId = Long.valueOf(String.valueOf(jwtProvider.getUid(token)));
        Post post  = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
        if (userId != post.getUser().getId()){
            throw new IllegalStateException("회원님은 게시글 작성자가 아니므로 수정이 불가능합니다.");
        }
        post.update(updateDto);
    }

    @Transactional
    public void deletePost(String token,Long postId){
        Long userId = Long.valueOf(String.valueOf(jwtProvider.getUid(token)));
        Post post  = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
        if (userId != post.getUser().getId()) {
            throw new IllegalStateException("회원님은 게시글 작성자가 아니므로 삭제가 불가능합니다.");
        }
        postRepository.deleteById(postId);

    }

    @Transactional
    public Page<PostListResponseDto> searchPost(String keyword, Pageable pageable){
        return postRepository.findAllByTitleContainingDto(keyword, pageable);
    }

    @Transactional
    public String postLike(String token,Long postId){
        Long userId = Long.valueOf(String.valueOf(jwtProvider.getUid(token)));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException("존재하지 않은 유저입니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalStateException("해당 게시글이 존재하지 않습니다."));
        if(user.getId() == post.getUser().getId()){
            throw new IllegalStateException("본인 게시글은 추천할 수 없습니다.");
        }
        PostLike postlike = postLikeRepository.findByPostAndUser(post, user);
        if(postlike == null){
            post.setLike(post.getLikes()+1);
            PostLike postLike = new PostLike(user, post);   // status를 true로 변환
            postLikeRepository.save(postLike);
            return "좋아요 완료";
        }else {
            postlike.unlikePost(post);
            postLikeRepository.delete(postlike);
            return "좋아요 취소";
        }
    }
}
