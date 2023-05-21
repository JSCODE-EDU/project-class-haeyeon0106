package com.example.ProjectClass.dto;

import com.example.ProjectClass.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,15}",
            message = "비밀번호는 8~15자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @Builder
    public UserRequestDto(String email,String password){
        this.email = email;
        this.password = password;
    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(new BCryptPasswordEncoder().encode(password))
                .build();
    }
}
