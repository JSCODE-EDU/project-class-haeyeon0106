package com.example.ProjectClass.controller;

import com.example.ProjectClass.dto.UserRequestDto;
import com.example.ProjectClass.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "멤버 API")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestBody UserRequestDto userRequestDto){
        userService.saveUser(userRequestDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }
}
