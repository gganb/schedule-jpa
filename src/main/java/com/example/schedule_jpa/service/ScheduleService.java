package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.schedule.request.ScheduleUpdateRequestDto;
import com.example.schedule_jpa.dto.schedule.response.ScheduleResponseDto;
import com.example.schedule_jpa.dto.schedule.request.ScheduleSaveRequestDto;
import com.example.schedule_jpa.dto.schedule.response.ScheduleSaveResponseDto;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.exception.CustomException;
import com.example.schedule_jpa.exception.ErrorCode;
import com.example.schedule_jpa.repository.ScheduleRepository;
import com.example.schedule_jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 저장
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto) {
        User findUser = userRepository.findUserByUsernameOrElseThrow(requestDto.getUsername());

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContents()
        );
        schedule.setUser(findUser);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleSaveResponseDto(savedSchedule.getId(),
                savedSchedule.getUser().getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt());
    }

    // id로 일정 단건 조회
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(),
                findSchedule.getUser().getUsername(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getCreatedAt(),
                findSchedule.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {

        List<Schedule> scheduleList = scheduleRepository.findAll();
        if (scheduleList.isEmpty()) {
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
        return scheduleList.stream().map(schedule -> new ScheduleResponseDto(
                schedule.getId(),
                schedule.getUser().getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        )).toList();
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto requestDto) {
        Schedule findschedule = scheduleRepository.findByIdOrElseThrow(id);

        if (requestDto.getTitle() == null && requestDto.getContents() == null) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
        }
        findschedule.update(requestDto.getTitle(), requestDto.getContents());

        return new ScheduleResponseDto(findschedule.getId(),
                findschedule.getUser().getUsername(),
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

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findScheduleByUser(Long userid) {

        List<Schedule> findList = scheduleRepository.findByUserIdOrderByUpdatedAtDesc(userid);
        if (findList.isEmpty()) {
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
        return findList.stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getUser().getUsername(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                )).toList();


    }
}
