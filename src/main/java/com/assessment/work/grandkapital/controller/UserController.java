package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandKapital_api.controllers.UsersApi;
import com.assessment.work.grandKapital_api.models.Page;
import com.assessment.work.grandKapital_api.models.User;
import com.assessment.work.grandKapital_api.models.UserUpdate;
import com.assessment.work.grandkapital.mapper.UserMapper;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.service.UserService;
import com.assessment.work.grandkapital.service.impl.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@Controller
@RequiredArgsConstructor
public class UserController implements UsersApi {

    // todo replace request userId param to jwt.userId
    private final UserService userService;
    private final UserMapper userMapper;

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
        ValidationUtils.validateDateFormatAndPhone(dateOfBirth, phone);
        return ResponseEntity.ok(userService.getPage(pageNumber, pageSize, dateOfBirth, phone, name, email));
    }

    @Override
    public ResponseEntity<User> updateUserData(Long userId, UserUpdate userUpdate) {
        return UsersApi.super.updateUserData(userId, userUpdate);
    }
}
