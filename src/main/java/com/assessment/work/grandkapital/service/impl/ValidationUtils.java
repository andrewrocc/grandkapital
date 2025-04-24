package com.assessment.work.grandkapital.service.impl;

import com.assessment.work.grandkapital.exception.ValidationException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class ValidationUtils {

    public void validatePhone(String phone) {
        if (StringUtils.hasText(phone) && !phone.matches("^7\\d{10}$")) {
            throw new ValidationException("Wrong phone format", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateDateFormatAndPhone(LocalDate dateOfBirth, String phone) {
        validatePhone(phone);

        if (dateOfBirth != null) {
            try {
                dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeException e) {
                throw new ValidationException("Wrong date format", HttpStatus.BAD_REQUEST);
            }
        }
    }
}