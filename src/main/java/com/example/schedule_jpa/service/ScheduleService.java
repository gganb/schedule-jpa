package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.ScheduleResponseDto;
import com.example.schedule_jpa.dto.schedule.ScheduleSaveRequestDto;
import com.example.schedule_jpa.dto.schedule.ScheduleSaveResponseDto;
import com.example.schedule_jpa.dto.schedule.ScheduleUpdateRequestDto;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.repository.ScheduleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final EntityManager em;

    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getUsername(),
                requestDto.getTitle(),
                requestDto.getContents()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleSaveResponseDto(savedSchedule.getId(),
                savedSchedule.getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt());
    }

    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(),
                findSchedule.getUsername(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getCreatedAt(),
                findSchedule.getCreatedAt());
    }

    public List<ScheduleResponseDto> findAll() {

        List<Schedule> scheduleList = scheduleRepository.findAll();
        if (scheduleList.isEmpty()) {
            throw new NoSuchElementException("일정이 존재하지 않습니다");
        }
        return scheduleList.stream().map(schedule -> new ScheduleResponseDto(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        )).toList();
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents) {
        Schedule findschedule = scheduleRepository.findByIdOrElseThrow(id);


        if (title == null && contents != null) {
            findschedule.updateContents(contents);
        } else if (title != null && contents == null) {
            findschedule.updateTitle(title);
        } else {
            findschedule.update(contents, title);
        }

        return new ScheduleResponseDto(findschedule.getId(),
                findschedule.getUsername(),
                findschedule.getTitle(),
                findschedule.getContents(),
                findschedule.getCreatedAt(),
                findschedule.getUpdatedAt());
    }

    @Transactional
    public String deleteSchedule(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
        return "[" + id + "] 글이 삭제되었습니다.";
    }
}
