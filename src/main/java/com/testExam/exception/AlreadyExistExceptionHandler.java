package com.testExam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlreadyExistExceptionHandler {

    @ExceptionHandler({AlreadyExistException.class})
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public Error handleAlreadyExistException(Exception ex) {
        return new Error(HttpStatus.CONFLICT, ex.getMessage());
    }

}
