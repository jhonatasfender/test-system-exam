package com.testExam.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ErrorTest {
    Error error = new Error(HttpStatus.CONTINUE, "message");

    @Test
    void testSetErrorCode() {
        error.setErrorCode(Integer.valueOf(0));
        error.getErrorCode();
    }

    @Test
    void testSetError() {
        error.setError("error");
        error.getError();
    }

    @Test
    void testSetErrorMessage() {
        error.setErrorMessage("errorMessage");
        error.getErrorMessage();
    }
}
