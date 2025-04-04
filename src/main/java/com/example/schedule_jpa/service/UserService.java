package com.example.schedule_jpa.service;


import com.example.schedule_jpa.dto.user.request.SignupRequestDto;
import com.example.schedule_jpa.dto.user.request.UpdateUserRequestDto;
import com.example.schedule_jpa.dto.user.response.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.exception.CustomException;
import com.example.schedule_jpa.exception.ErrorCode;
import com.example.schedule_jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(SignupRequestDto requestDto) {
        // 이메일 중복 검사
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }


        User user = new User(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
    }

    public List<UserResponseDto> findAllUser() {
        List<User> findUserList = userRepository.findAll();

        if (findUserList.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        return findUserList.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()
                )).toList();

    }

    public UserResponseDto findUserById(Long id) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);

        return new UserResponseDto(
                findUser.getId(),
                findUser.getUsername(),
                findUser.getEmail(),
                findUser.getCreatedAt(),
                findUser.getUpdatedAt());
    }

    @Transactional
    public UserResponseDto updateUser(String username, UpdateUserRequestDto requestDto) {
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        findUser.update(requestDto.getUsername(), requestDto.getEmail());

        return new UserResponseDto(
                findUser.getId(),
                findUser.getUsername(),
                findUser.getEmail(),
                findUser.getCreatedAt(),
                findUser.getUpdatedAt());
    }

    public String deleteUser(String username) {
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        userRepository.delete(findUser);

        return "삭제되었습니다";
    }
}
