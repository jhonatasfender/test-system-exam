package com.testExam.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Message message) {
        super(message.getMessage());
    }
}