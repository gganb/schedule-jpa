package com.example.schedule_jpa.dto.user.request;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 4, message = "최대 4글자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]+$", message = "형식이 올바르지 않습니다.")
    private final String username;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    private final String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String password;

    public SignupRequestDto(String username,String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
