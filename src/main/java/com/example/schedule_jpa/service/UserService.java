package com.example.schedule_jpa.service;


import com.example.schedule_jpa.dto.user.SignupRequestDto;
import com.example.schedule_jpa.dto.user.UpdateUserRequestDto;
import com.example.schedule_jpa.dto.user.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(SignupRequestDto requestDto) {
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
            throw new NoSuchElementException("유저 정보가 없습니다");
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
