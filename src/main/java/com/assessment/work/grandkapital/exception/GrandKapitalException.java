package com.assessment.work.grandkapital.exception;

import com.assessment.work.grandkapital.model.dto.Error;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class GrandKapitalException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final Error errorMessage;

    public GrandKapitalException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorMessage = new Error().message(message).status(this.httpStatus.value());
    }

    public GrandKapitalException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorMessage = new Error().message(message).status(httpStatus.value());
    }
}