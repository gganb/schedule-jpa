package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.ScheduleResponseDto;
import com.example.schedule_jpa.dto.schedule.ScheduleSaveRequestDto;
import com.example.schedule_jpa.dto.schedule.ScheduleSaveResponseDto;
import com.example.schedule_jpa.dto.schedule.ScheduleUpdateRequestDto;
import com.example.schedule_jpa.service.ScheduleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleService scheduleService;

    @PersistenceContext
    private final EntityManager em;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(
            @Valid @RequestBody ScheduleSaveRequestDto requestDto
    ) {
        ScheduleSaveResponseDto savedSchedule = scheduleService.saveSchedule(requestDto);
        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(
            @PathVariable Long id
    ) {
        ScheduleResponseDto findSchedule = scheduleService.findScheduleById(id);
        return new ResponseEntity<>(findSchedule, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleList, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        ScheduleResponseDto updateSchedule = scheduleService.updateSchedule(id,requestDto.getTitle(),requestDto.getContents());
        return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id
    ){
        String msg = scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
