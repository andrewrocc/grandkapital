package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandkapital.model.dto.Email;
import com.assessment.work.grandkapital.model.dto.Message;
import com.assessment.work.grandkapital.service.EmailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class EmailController {

    private final EmailService emailService;

    /**
     * POST /emails : Add email to user
     *
     * @param userId    ID of the user (required)
     * @param email     (required)
     * @return Email added successfully (status code 200)
     *         or bad request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/emails",
            produces = { "application/json" }
    )
    public ResponseEntity<List<Email>> addUserEmail(@NotNull @Valid Long userId,
                                                    @NotNull @Valid String email) {
        return ResponseEntity.ok(emailService.addEmail(userId, email));
    }

    /**
     * DELETE /emails : Remove user email
     *
     * @param userId    ID of the user (required)
     * @param email     email to delete (required)
     * @return Email removed successfully (status code 200)
     *         or bad request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/emails",
            produces = { "application/json" }
    )
    public ResponseEntity<Message> removeUserEmail(@NotNull @Valid Long userId,
                                                   @NotNull @Valid String email) {
        emailService.removeEmail(userId, email);
        return ResponseEntity.ok(new Message().message("Success"));
    }

    /**
     * PUT /emails : Replace email with new one
     *
     * @param userId    ID of the user (required)
     * @param oldEmail  email to need replace (required)
     * @param newEmail  new one (required)
     * @return Email replaced successfully (status code 200)
     *         or bad request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/emails",
            produces = { "application/json" }
    )
    public ResponseEntity<Message> changeUserEmail(@NotNull @Valid Long userId,
                                                   @NotNull @Valid String oldEmail,
                                                   @NotNull @Valid String newEmail) {
        emailService.changeEmail(userId, oldEmail, newEmail);
        return ResponseEntity.ok(new Message().message("Success"));
    }
}