package com.testExam.exception;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(Message message) {
        super(message.getMessage());
    }

}