package com.example.ProjectClass.controller;

import com.example.ProjectClass.dto.PostListResponseDto;
import com.example.ProjectClass.dto.PostResponseDto;
import com.example.ProjectClass.dto.PostSaveRequestDto;
import com.example.ProjectClass.dto.PostUpdateDto;
import com.example.ProjectClass.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<PostListResponseDto>> getPostAll(@PageableDefault(size = 100,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(postService.getPostAll(pageable));
    }

    @GetMapping("{id}/detail")
    public ResponseEntity<PostResponseDto> getPostDetail(@PathVariable Long id){
        PostResponseDto postDetail = postService.getPostDetail(id);
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
