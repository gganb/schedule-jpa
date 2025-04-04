package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.user.request.LoginRequestDto;
import com.example.schedule_jpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    // 이메일, 비밀번호를 받아서 서비스에 넘김. > requestbody
    // 받아오는건 세션 id. 전달할때도 세션id를 전달 ..
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequestDto dto, HttpServletRequest request) {

        Long userId = userService.handleLogin(dto);

        HttpSession session = request.getSession(); // 신규 세션 생성, JSESSIONID 발급
        session.setAttribute("LOGIN_USER", userId); // 서버 메모리에 세션 저장

        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션 없으면 생성안함
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}
