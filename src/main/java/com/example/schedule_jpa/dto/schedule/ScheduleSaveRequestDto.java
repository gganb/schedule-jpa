package com.example.schedule_jpa.dto.schedule;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class ScheduleSaveRequestDto {

    @NotNull(message = "이름을 입력해주세요.")
    @Size(max = 4,message = "최대 4글자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$",message = "한글 또는 영문만 입력 가능합니다.")
    private final String username;

    @NotNull(message = "제목을 입력해주세요.")
    @Size(max = 10, message = "최대 10 글자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$",message = "한글 또는 영문만 입력 가능합니다.")
    private final String title;

    @NotBlank(message = "할 일을 입력해주세요.")
    private final String contents;

    public ScheduleSaveRequestDto(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
