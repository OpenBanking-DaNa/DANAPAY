package com.dana.danapay.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ApiExceptionDTO {

    private int status;         // 코드
    private String message;     // 에러 메세지

    public ApiExceptionDTO() {
    }

    public ApiExceptionDTO(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;

    }

    public ApiExceptionDTO(int status, String message) {
        this.status = status;
        this.message = message;

    }
}
