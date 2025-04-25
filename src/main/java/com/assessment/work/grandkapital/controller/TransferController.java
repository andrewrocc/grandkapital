package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandkapital.model.dto.Message;
import com.assessment.work.grandkapital.model.dto.TransferRequest;
import com.assessment.work.grandkapital.exception.GrandKapitalException;
import com.assessment.work.grandkapital.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    /**
     * POST /transfer : Transfer money from one to another user
     *
     * @param transferRequest       (required)
     * @return                      "Success" (status code 200)
     *                              or bad request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/transfer",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<Message> transfer(@Valid @RequestBody TransferRequest transferRequest) {
        Long fromUserId = getAuthenticationUserId();
        transferService.transfer(transferRequest, fromUserId);
        return ResponseEntity.ok(new Message().message("Transfer successful"));
    }

    private Long getAuthenticationUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
            throw new GrandKapitalException("User is not authenticated");

        Object principal = authentication.getPrincipal();
        if (principal instanceof Long userId)
            return userId;
        else
            throw new GrandKapitalException("Invalid authentication principal");
    }
}