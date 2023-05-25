package com.example.ProjectClass.controller;

import com.example.ProjectClass.dto.PostListResponseDto;
import com.example.ProjectClass.dto.PostResponseDto;
import com.example.ProjectClass.dto.PostSaveRequestDto;
import com.example.ProjectClass.dto.PostUpdateDto;
import com.example.ProjectClass.service.PostService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;


@Api(tags = "익명 게시판 API")
@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "게시글 작성하기")
    @PostMapping("/upload")
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 정상적으로 작성"),
            @ApiResponse(code = 201,message = "정상 요청으로 인해 새 리소스 생성"),
            @ApiResponse(code = 400, message = "잘못된 요청으로 게시글 작성 오류"),
            @ApiResponse(code = 401, message = "인증에 의한 오류"),
            @ApiResponse(code = 404,message = "요청 리소스를 찾을 수 없습니다")
    })
    public ResponseEntity savePost(@RequestHeader(value = "Authorization")String token,
                                   @Validated @RequestBody PostSaveRequestDto postSaveRequestDto){
        postService.savePost(token,postSaveRequestDto);
        return ResponseEntity.ok("게시글이 작성되었습니다.");
    }

    @ApiOperation(value = "전체 게시글 조회하기")
    @GetMapping("")
    public ResponseEntity<Page<PostListResponseDto>> getPostAll(@PageableDefault(size = 100,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(postService.getPostAll(pageable));
    }

    @ApiOperation(value = "특정 게시글 조회하기")
    @GetMapping("{id}/detail")
    @ApiImplicitParam(name = "id",value = "게시글 번호")
    public ResponseEntity<PostResponseDto> getPostDetail(@PathVariable Long id){
        PostResponseDto postDetail = postService.getPostDetail(id);
        return ResponseEntity.ok(postDetail);
    }

    @ApiOperation(value = "특정 게시글 수정하기")
    @PutMapping("{id}/update")
    @ApiImplicitParam(name = "id",value = "게시글 번호")
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 정상적으로 수정"),
            @ApiResponse(code = 201,message = "정상 요청으로 인해 새 리소스 생성"),
            @ApiResponse(code = 400, message = "잘못된 요청으로 게시글 수정 오류"),
            @ApiResponse(code = 401, message = "인증에 의한 오류"),
            @ApiResponse(code = 404,message = "요청 리소스를 찾을 수 없습니다")
    })
    public ResponseEntity updatePost(@RequestHeader(value = "Authorization")String token,
                                     @PathVariable Long id,
                                     @RequestBody PostUpdateDto updateDto){
        postService.updatePost(token,id,updateDto);
        return new ResponseEntity<>("게시글이 수정되었습니다.",HttpStatus.OK);
    }

    @ApiOperation(value = "특정 게시글 삭제하기")
    @DeleteMapping("{id}/delete")
    @ApiImplicitParam(name = "id",value = "게시글 번호")
    public ResponseEntity<String> deletePost(@RequestHeader(value = "Authorization")String token,
                                             @PathVariable Long id){
        postService.deletePost(token,id);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    @ApiOperation(value = "게시글 검색하기")
    @GetMapping("/search")
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 정상적으로 검색"),
            @ApiResponse(code = 400, message = "잘못된 요청으로 게시글 검색 오류"),
            @ApiResponse(code = 401, message = "인증에 의한 오류"),
            @ApiResponse(code = 404,message = "요청 리소스를 찾을 수 없습니다")
    })
    public ResponseEntity<Page<PostListResponseDto>> searchPost(@NotBlank String keyword
            ,@PageableDefault(size = 100,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<PostListResponseDto> search = postService.searchPost(keyword, pageable);
        return ResponseEntity.ok(search);
    }
}
