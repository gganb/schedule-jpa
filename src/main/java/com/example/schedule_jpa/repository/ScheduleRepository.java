package com.example.schedule_jpa.repository;

import com.example.schedule_jpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당하는 일정을 찾을 수 없습니다."));
    }


}
