package com.example.schedule_jpa.dto.schedule.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    @Size(max = 10, message = "최대 10 글자까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]+$",message = "형식이 올바르지 않습니다.")
    private final String title;

    private final String contents;

    public ScheduleUpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
