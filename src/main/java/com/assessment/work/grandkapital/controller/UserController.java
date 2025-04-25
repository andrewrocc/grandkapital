package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandkapital.model.dto.Page;
import com.assessment.work.grandkapital.model.dto.User;
import com.assessment.work.grandkapital.mapper.UserMapper;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.service.UserService;
import com.assessment.work.grandkapital.utils.ValidationUtils;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Validated
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * GET /users/{userId} : Get user by ID
     *
     * @param userId ID of the user (required)
     * @return User details (status code 200)
     *         or bad request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/{userId}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<User> getUserById(@Valid @Parameter(required = true) @PathVariable("userId") Long userId) {
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
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<Page> getUserPage(@NotNull @Min(0) @Valid Integer pageNumber,
                                            @NotNull @Min(0) @Valid Integer pageSize,
                                            @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                            @Pattern(regexp = "^7\\d{10}$") @Valid String phone,
                                            @Size(min = 1) @Valid String name,
                                            @Valid String email) {
        ValidationUtils.validateDateFormatAndPhone(dateOfBirth, phone);
        return ResponseEntity.ok(userService.getPage(pageNumber, pageSize, dateOfBirth, phone, name, email));
    }
}