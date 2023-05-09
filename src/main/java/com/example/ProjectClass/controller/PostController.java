package com.example.ProjectClass.controller;

import com.example.ProjectClass.dto.PostDetailResponseDto;
import com.example.ProjectClass.dto.PostListResponseDto;
import com.example.ProjectClass.dto.PostSaveRequestDto;
import com.example.ProjectClass.dto.PostUpdateDto;
import com.example.ProjectClass.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/upload")
    public ResponseEntity savePost(@RequestBody PostSaveRequestDto postSaveRequestDto){
        postService.savePost(postSaveRequestDto);
        return ResponseEntity.ok("게시글이 작성되었습니다.");
    }

    @GetMapping("")
    public ResponseEntity<List<PostListResponseDto>> getPostAll(){
        return ResponseEntity.ok(postService.getPostAll());
    }

    @GetMapping("{id}/detail")
    public ResponseEntity<PostDetailResponseDto> getPostDetail(@PathVariable Long id){
        PostDetailResponseDto postDetail = postService.getPostDetail(id);
        return ResponseEntity.ok(postDetail);
    }

    @PutMapping("{id}/update")
    public ResponseEntity updatePost(@PathVariable Long id, @RequestBody PostUpdateDto updateDto){
        postService.updatePost(id,updateDto);
        return new ResponseEntity<>("게시글이 수정되었습니다.",HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }
}
