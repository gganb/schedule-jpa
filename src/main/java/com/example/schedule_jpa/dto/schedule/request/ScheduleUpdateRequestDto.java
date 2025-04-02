package com.example.schedule_jpa.dto.schedule.request;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    private final String title;

    private final String contents;

    public ScheduleUpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
