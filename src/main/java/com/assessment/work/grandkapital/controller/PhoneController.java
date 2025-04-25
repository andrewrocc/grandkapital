package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandkapital.model.dto.Message;
import com.assessment.work.grandkapital.model.dto.Phone;
import com.assessment.work.grandkapital.service.PhoneService;
import com.assessment.work.grandkapital.utils.ValidationUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Validated
@Controller
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;

    /**
     * POST /phones : Add phone to user
     *
     * @param userId    user id (required)
     * @param phone     new phone (required)
     * @return Phone added successfully (status code 200)
     *         or bad request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/phones",
            produces = { "application/json" }
    )
    public ResponseEntity<List<Phone>> addUserPhone(@NotNull @Valid Long userId,
                                                    @NotNull @Pattern(regexp = "^7\\d{10}$") @Valid String phone) {
        ValidationUtils.validatePhone(phone);
        return ResponseEntity.ok(phoneService.addPhone(userId, phone));
    }

    /**
     * PUT /phones : Replace phone with new one
     *
     * @param userId        user id (required)
     * @param oldPhone      phone to need replace (required)
     * @param newPhone      new one (required)
     * @return Phone number change successfully (status code 200)
     *         or bad request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phones",
            produces = { "application/json" }
    )
    public ResponseEntity<Message> changeUserPhone(@NotNull @Valid Long userId,
                                                   @NotNull @Valid String oldPhone,
                                                   @NotNull @Pattern(regexp = "^7\\d{10}$") @Valid String newPhone) {
        ValidationUtils.validatePhone(oldPhone);
        ValidationUtils.validatePhone(newPhone);
        phoneService.changePhone(userId, oldPhone, newPhone);
        return ResponseEntity.ok(new Message().message("Success"));
    }

    /**
     * DELETE /phones : Remove user phone
     *
     * @param userId    ID of the user (required)
     * @param phone     phone to delete (required)
     * @return Phone removed successfully (status code 200)
     *         or bad request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/phones",
            produces = { "application/json" }
    )
    public ResponseEntity<Message> removeUserPhone(@NotNull @Valid Long userId,
                                                   @NotNull @Pattern(regexp = "^7\\d{10}$")@Valid String phone) {
        ValidationUtils.validatePhone(phone);
        phoneService.removePhone(userId, phone);
        return ResponseEntity.ok(new Message().message("Success"));
    }
}