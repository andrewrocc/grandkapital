package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandKapital_api.controllers.EmailsApi;
import com.assessment.work.grandKapital_api.models.Email;
import com.assessment.work.grandKapital_api.models.Message;
import com.assessment.work.grandkapital.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Controller
@RequiredArgsConstructor
public class EmailController implements EmailsApi {

    private final EmailService emailService;

    /**
     * POST /emails : Add email to user
     *
     * @param userId    ID of the user (required)
     * @param email     (required)
     * @return Email added successfully (status code 200)
     *         or bad request (status code 400)
     */
    @Override
    public ResponseEntity<List<Email>> addUserEmail(Long userId, String email) {
        return ResponseEntity.ok(emailService.addEmail(userId, email));
    }

    /**
     * DELETE /emails : Remove user email
     *
     * @param userId ID of the user (required)
     * @param email  (required)
     * @return Email removed successfully (status code 200)
     *         or bad request (status code 400)
     */
    @Override
    public ResponseEntity<Message> removeUserEmail(Long userId, String email) {
        emailService.removeEmail(userId, email);
        return ResponseEntity.ok(new Message().message("Success"));
    }

    /**
     * PUT /emails : Replace email with new one
     *
     * @param userId ID of the user (required)
     * @param oldEmail  (required)
     * @param newEmail  (required)
     * @return Email replaced successfully (status code 200)
     *         or bad request (status code 400)
     */
    @Override
    public ResponseEntity<Message> changeUserEmail(Long userId, String oldEmail, String newEmail) {
        emailService.changeEmail(userId, oldEmail, newEmail);
        return ResponseEntity.ok(new Message().message("Success"));
    }
}