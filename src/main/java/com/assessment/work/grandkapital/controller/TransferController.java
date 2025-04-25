package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandKapital_api.controllers.TransferApi;
import com.assessment.work.grandKapital_api.models.Message;
import com.assessment.work.grandKapital_api.models.TransferRequest;
import com.assessment.work.grandkapital.exception.GrandKapitalException;
import com.assessment.work.grandkapital.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TransferController implements TransferApi {

    private final TransferService transferService;

    /**
     * POST /transfer : Transfer money from one to another user
     *
     * @param transferRequest       (required)
     * @return                      "Success" (status code 200)
     *                              or bad request (status code 400)
     */
    @Override
    public ResponseEntity<Message> transfer(TransferRequest transferRequest) {
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