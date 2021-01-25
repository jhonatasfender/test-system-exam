package com.testExam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PermissionExceptionHandler {

    @ExceptionHandler({PermissionException.class, CoinsException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Error handlePermissionException(Exception ex) {
        return new Error(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

}
