package com.example.ProjectClass.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    private String password;
}
