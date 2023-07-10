package com.example.demo.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "멤버를 찾을 수 없습니다."),
    MEMBER_EXISTS(409, "이미 가입된 메일입니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
