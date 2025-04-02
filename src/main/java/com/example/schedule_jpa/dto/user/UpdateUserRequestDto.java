package com.example.schedule_jpa.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    @NotNull(message = "이름을 입력해주세요.")
    @Size(max = 4, message = "4글자 이내로 입력해주세요")
    private final String username;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    private final String email;


    public UpdateUserRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
