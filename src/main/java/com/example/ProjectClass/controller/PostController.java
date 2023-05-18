package com.example.ProjectClass.controller;

import com.example.ProjectClass.dto.PostListResponseDto;
import com.example.ProjectClass.dto.PostResponseDto;
import com.example.ProjectClass.dto.PostSaveRequestDto;
import com.example.ProjectClass.dto.PostUpdateDto;
import com.example.ProjectClass.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = "익명 게시판 API")
@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "게시글 작성하기")
    @PostMapping("/upload")
    public ResponseEntity savePost(@Validated @RequestBody PostSaveRequestDto postSaveRequestDto){
        postService.savePost(postSaveRequestDto);
        return ResponseEntity.ok("게시글이 작성되었습니다.");
    }

    @ApiOperation(value = "전체 게시글 조회하기")
    @GetMapping("")
    public ResponseEntity<Page<PostListResponseDto>> getPostAll(@PageableDefault(size = 100,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(postService.getPostAll(pageable));
    }

    @ApiOperation(value = "특정 게시글 조회하기")
    @GetMapping("{id}/detail")
    public ResponseEntity<PostResponseDto> getPostDetail(@PathVariable Long id){
        PostResponseDto postDetail = postService.getPostDetail(id);
        return ResponseEntity.ok(postDetail);
    }

    @ApiOperation(value = "특정 게시글 수정하기")
    @PutMapping("{id}/update")
    public ResponseEntity updatePost(@PathVariable Long id, @RequestBody PostUpdateDto updateDto){
        postService.updatePost(id,updateDto);
        return new ResponseEntity<>("게시글이 수정되었습니다.",HttpStatus.OK);
    }

    @ApiOperation(value = "특정 게시글 삭제하기")
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    @ApiOperation(value = "게시글 검색하기")
    @GetMapping("/search")
    public ResponseEntity<Page<PostListResponseDto>> searchPost(String keyword
            ,@PageableDefault(size = 100,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<PostListResponseDto> search = postService.searchPost(keyword, pageable);
        return ResponseEntity.ok(search);
    }
}
