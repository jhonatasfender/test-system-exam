package com.testExam.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class Error {
    private Integer errorCode;
    private String error;
    private String errorMessage;

    public Error(HttpStatus status, String message) {
        this.errorCode = status.value();
        this.error = status.name();
        this.errorMessage = message;
    }
}