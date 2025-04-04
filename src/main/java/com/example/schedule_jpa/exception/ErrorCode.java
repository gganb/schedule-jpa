package com.example.schedule_jpa.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //Common
    INVALID_INPUT_VALUE(400, "Bad Request", "C001", "잘못된 입력값 입니다."),
    ENTITY_NOT_FOUND(400, "Bad Request", "C003", "존재하지 않는 데이터입니다."),
    INTERNAL_SERVER_ERROR(500, "Server Error", "C004", "서버 내부 오류가 발생했습니다."),
    INVALID_TYPE_VALUE(400, "Bad Request", "C005", "요청 타입이 유효하지 않습니다."),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "C006", "허용되지 않은 HTTP 메서드입니다."),
    ACCESS_DENIED(403, "Forbidden", "C008", "접근 권한이 없습니다."),

    // User
    DUPLICATED_EMAIL(400, "Bad Request", "U001", "이미 등록된 이메일입니다."),
    UNAUTHORIZED(401, "Unauthorized", "U004", "인증이 필요합니다."),
    USER_NOT_FOUND(404, "Not Found", "U002", "회원을 찾을 수 없습니다."),

    //schedule
    SCHEDULE_NOT_FOUND(404, "Not Found", "S001", "일정을 찾을 수 없습니다.");


    private final int status;
    private final String error;
    private final String code;
    private final String message;

}
