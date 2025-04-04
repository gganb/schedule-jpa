package com.example.schedule_jpa.repository;

import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.exception.CustomException;
import com.example.schedule_jpa.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String usernam);

    default User findUserByUsernameOrElseThrow(String username) {
        return findUserByUsername(username).orElseThrow(
                () ->  new CustomException(ErrorCode.USER_NOT_FOUND));

    }

    default User findUserByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    boolean existsByEmail(String email);
}
