package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandKapital_api.controllers.PhonesApi;
import com.assessment.work.grandKapital_api.models.Message;
import com.assessment.work.grandKapital_api.models.Phone;
import com.assessment.work.grandkapital.service.PhoneService;
import com.assessment.work.grandkapital.service.impl.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Controller
@RequiredArgsConstructor
public class PhoneController implements PhonesApi {

    private final PhoneService phoneService;

    /**
     * POST /phones : Add phone to user
     *
     * @param userId    user id (required)
     * @param phone     new phone (required)
     * @return Phone added successfully (status code 200)
     *         or bad request (status code 400)
     */
    @Override
    public ResponseEntity<List<Phone>> addUserPhone(Long userId, String phone) {
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
    @Override
    public ResponseEntity<Message> changeUserPhone(Long userId, String oldPhone, String newPhone) {
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
    @Override
    public ResponseEntity<Message> removeUserPhone(Long userId, String phone) {
        ValidationUtils.validatePhone(phone);
        phoneService.removePhone(userId, phone);
        return ResponseEntity.ok(new Message().message("Success"));
    }
}