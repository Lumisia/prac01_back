package com.example.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BaseResponseStatus {
    //  2000번대 성공
    Success(true, 2000, "요청이 성공했습니다."),

    //  3000번대 클라이언트 입력 오류, 입력값 검증 오류
    JWT_EXPIRED(false, 5000, "JWT 토큰이 만료됐습니다."),
    JWT_INVALID(false, 5000, "JWT 토큰이 유효하지 않습니다."),
    SIGNUP_DUPLICATE_EMAIL(false, 5000, "중복된 이메일입니다."),
    SIGNUP_INVALID_PASSWORD(false, 5000, "비밀번호는 대,소문자, 숫자, 특수문자가 포함돼야함."),
    SIGNUP_INVALID_USERNAME(false, 5001, "유저가 음슴");

    private final boolean success;
    private final int code;
    private final String message;
}
