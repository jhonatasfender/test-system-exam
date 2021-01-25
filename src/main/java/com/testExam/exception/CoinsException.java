package com.testExam.exception;

public class CoinsException extends RuntimeException {

    public CoinsException(Message message) {
        super(message.getMessage());
    }
}