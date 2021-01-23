package com.testExam.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class HandlerExceptionHandler {

    @ExceptionHandler({
        RuntimeException.class,
        NullPointerException.class,
        IllegalArgumentException.class,
        MethodArgumentNotValidException.class
    })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleMethodArgumentNotValidException(Exception ex) {
        return new Error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @Getter
    @Setter
    public static class Error {
        private Integer errorCode;
        private String error;
        private String errorMessage;

        public Error(HttpStatus status, String message) {
            this.errorCode = status.value();
            this.error = status.name();
            this.errorMessage = message;
        }
    }
}
