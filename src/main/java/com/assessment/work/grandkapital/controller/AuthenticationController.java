package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandkapital.model.dto.LoginRequest;
import com.assessment.work.grandkapital.model.dto.Message;
import com.assessment.work.grandkapital.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * POST /auth : Authentication entrypoint (get jwt token)
     *
     * @param loginRequest  user credentials (required)
     * @return              Login successfully (status code 200)
     *                      or Forbidden (status code 403)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/auth",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<Message> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticationService.login(loginRequest);
        return ResponseEntity.ok(new Message().message(token));
    }
}