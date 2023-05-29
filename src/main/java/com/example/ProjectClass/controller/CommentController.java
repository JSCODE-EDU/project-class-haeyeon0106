package com.example.ProjectClass.controller;

import com.example.ProjectClass.dto.CommentRequestDto;
import com.example.ProjectClass.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @ApiOperation("댓글 등록")
    @PostMapping("/post/{id}/comment/upload")
    public ResponseEntity<String> saveComment(@RequestHeader(value = "Authorization")String token,
                                              @PathVariable Long id,
                                              @RequestBody CommentRequestDto commentRequestDto
                            ){
        commentService.saveComment(token,id,commentRequestDto);
        return ResponseEntity.ok("댓글 등록 성공");
    }
}
