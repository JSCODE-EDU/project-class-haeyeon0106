package com.example.ProjectClass.service;

import com.example.ProjectClass.domain.User;
import com.example.ProjectClass.dto.UserLoginRequestDto;
import com.example.ProjectClass.dto.UserRequestDto;
import com.example.ProjectClass.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void saveUser(UserRequestDto userRequestDto){
        boolean existEmail = userRepository.existsByEmail(userRequestDto.getEmail());
        if(existEmail){
            throw new IllegalArgumentException("존재하는 이메일입니다.");
        }
        userRepository.save(userRequestDto.toEntity());
    }

    @Transactional
    public void loginUser(UserLoginRequestDto requestDto){
        User user = userRepository.findByEmail(requestDto.getEmail());
        
        // 이메일, 비밀번호 일치확인
        if(user == null){
            throw new IllegalStateException("이메일이 일치하지 않습니다.");
        } else if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }
}
