package com.example.schedule_jpa.dto.schedule.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

   private final String username;

    @NotNull(message = "제목을 입력해주세요.")
    @Size(max = 10, message = "최대 10 글자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]+$",message = "형식이 올바르지 않습니다.")
    private final String title;

    @NotBlank(message = "할 일을 입력해주세요.")
    private final String contents;

    public ScheduleSaveRequestDto(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
