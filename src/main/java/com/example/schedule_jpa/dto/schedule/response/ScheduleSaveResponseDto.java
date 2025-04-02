package com.example.schedule_jpa.dto.schedule.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;

    public ScheduleSaveResponseDto(Long id, String username, String title, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
