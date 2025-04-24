package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandKapital_api.controllers.UsersApi;
import com.assessment.work.grandKapital_api.models.Email;
import com.assessment.work.grandKapital_api.models.Page;
import com.assessment.work.grandKapital_api.models.Phone;
import com.assessment.work.grandKapital_api.models.User;
import com.assessment.work.grandKapital_api.models.UserUpdate;
import com.assessment.work.grandkapital.exception.ValidationException;
import com.assessment.work.grandkapital.mapper.UserMapper;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Validated
@Controller
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<User> addUserEmail(Long userId, Email email) {
        return UsersApi.super.addUserEmail(userId, email);
    }

    @Override
    public ResponseEntity<User> addUserPhone(Long userId, Phone phone) {
        return UsersApi.super.addUserPhone(userId, phone);
    }

    /**
     * GET /users/{userId} : Get user by ID
     *
     * @param userId ID of the user (required)
     * @return User details (status code 200)
     *         or bad request (status code 400)
     */
    @Override
    public ResponseEntity<User> getUserById(Long userId) {
        UserEntity user = userService.getUserById(userId);
        return ResponseEntity.ok(userMapper.mapToDto(user));
    }

    /**
     * GET /users : Search users with filtering and pagination
     *
     * @param pageNumber        page number (required)
     * @param pageSize          Number of users, returned to each page (required)
     * @param dateOfBirth       Filter users born after this date (format: DD.MM.YYYY) (optional)
     * @param phone             Filter by exact phone match (format: 79201234567) (optional)
     * @param name              Filter by names starting with the value (optional)
     * @param email             Filter by exact email match (optional)
     * @return List of users matching criteria (status code 200)
     *         or bad request (status code 400)
     */
    @Override
    public ResponseEntity<Page> getUserPage(Integer pageNumber, Integer pageSize, LocalDate dateOfBirth, String phone,
                                            String name, String email) {
        validateRequestData(dateOfBirth, phone);
        return ResponseEntity.ok(userService.getPage(pageNumber, pageSize, dateOfBirth, phone, name, email));
    }

    private void validateRequestData(LocalDate dateOfBirth, String phone) {
        if (StringUtils.hasText(phone) && !phone.matches("^7\\d{10}$")) {
            throw new ValidationException("Wrong phone format", HttpStatus.BAD_REQUEST);
        }

        if (dateOfBirth != null) {
            try {
                dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeException e) {
                throw new ValidationException("Wrong date format", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public ResponseEntity<Void> removeUserEmail(Long userId, String email) {
        return UsersApi.super.removeUserEmail(userId, email);
    }

    @Override
    public ResponseEntity<Void> removeUserPhone(Long userId, String phone) {
        return UsersApi.super.removeUserPhone(userId, phone);
    }

    @Override
    public ResponseEntity<User> updateUserData(Long userId, UserUpdate userUpdate) {
        return UsersApi.super.updateUserData(userId, userUpdate);
    }
}
