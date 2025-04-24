package com.assessment.work.grandkapital.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends GrandKapitalException {

    public ValidationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
