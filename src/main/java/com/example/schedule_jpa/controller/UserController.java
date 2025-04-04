package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.user.request.SignupRequestDto;
import com.example.schedule_jpa.dto.user.request.UpdateUserRequestDto;
import com.example.schedule_jpa.dto.user.response.UserResponseDto;
import com.example.schedule_jpa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> saveUser(
            @Valid @RequestBody SignupRequestDto requestDto
    ) {
        log.info("user 생성 api");
        UserResponseDto saveUser = userService.saveUser(requestDto);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUser() {
        List<UserResponseDto> userList = userService.findAllUser();
        log.info("user 전체조회 api");
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(
            @PathVariable Long id
    ) {
        UserResponseDto findUser = userService.findUserById(id);
        log.info("user 조회 api");
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable String username,
            @RequestBody UpdateUserRequestDto requestDto
    ) {
        log.info("user 수정 api");
        UserResponseDto updateUser = userService.updateUser(username, requestDto);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userid
    ) {
        log.info("user 삭제 api");
        String msg = userService.deleteUser(userid);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
