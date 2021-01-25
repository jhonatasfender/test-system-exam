package com.testExam.exception;

public class PermissionException extends RuntimeException {

    public PermissionException(Message message) {
        super(message.getMessage());
    }
}