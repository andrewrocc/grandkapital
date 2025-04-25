package com.assessment.work.grandkapital.exception;

import com.assessment.work.grandkapital.model.dto.Error;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = GrandKapitalException.class)
    protected ResponseEntity<Error> handleErrors(GrandKapitalException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).contentType(MediaType.APPLICATION_JSON)
                .body(new Error().message(ex.getMessage()).status(ex.getHttpStatus().value()));
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Error> handleErrors(ValidationException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).contentType(MediaType.APPLICATION_JSON)
                .body(new Error().message(ex.getMessage()).status(ex.getHttpStatus().value()));
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    protected Error handleConstraintViolation(ConstraintViolationException ex, HttpServletResponse res) {
        log.error("", ex);
        final Error errorMessage = new Error().message("Формат параметров запроса не соответствует протоколу: " + ex.getMessage());
        res.setStatus(Optional.ofNullable(errorMessage.getStatus()).orElse(HttpStatus.BAD_REQUEST.value()));
        return errorMessage;
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleGlobalException(final AuthenticationException ex) {
        log.error("error while authentication process. {}", ex.getMessage());
        Error errorMessage = new Error().message(ex.getMessage()).status(HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity
                .status(errorMessage.getStatus())
                .body(errorMessage);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Error> handleErrors(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                .body(new Error().message(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Missing required parameters", ex);
        return ResponseEntity.status(status).headers(headers).contentType(MediaType.APPLICATION_JSON)
                .body(new Error().message("Missing required parameters: " + ex.getLocalizedMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        log.error("Not valid request parameter", ex);
        return ResponseEntity.status(status).headers(headers).contentType(MediaType.APPLICATION_JSON)
                .body(new Error().message("Not valid request parameter: " + ex.getBindingResult().getFieldErrors()
                        .stream().map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                        .collect(Collectors.joining(", "))));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        log.error("Not readable request body", ex);
        return ResponseEntity.status(status).headers(headers).contentType(MediaType.APPLICATION_JSON)
                .body(new Error().message("Not readable request body: " + ex.getMostSpecificCause().getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error("Unknown error", ex);
        return ResponseEntity.status(status).headers(headers).contentType(MediaType.APPLICATION_JSON)
                .body(new Error().message("Unknown error: " + ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error("Wrong type parameter", ex);
        return ResponseEntity.status(status).headers(headers).contentType(MediaType.APPLICATION_JSON)
                .body(new Error().message("Wrong type parameter: " + ex.getMessage()).status(HttpStatus.BAD_REQUEST.value()));
    }
}