package com.example.schedule_jpa.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "필수 입력 값 입니다.")
    private String email;

    @NotBlank(message = "필수 입력 값 입니다.")
    private String password;
}
