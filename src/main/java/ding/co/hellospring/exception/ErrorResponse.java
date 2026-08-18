package ding.co.hellospring.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String errorCode;
    private String message;

    // 생성자
    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
