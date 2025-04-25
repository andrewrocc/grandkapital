package com.assessment.work.grandkapital.controller;

import com.assessment.work.grandKapital_api.controllers.AuthApi;
import com.assessment.work.grandKapital_api.models.LoginRequest;
import com.assessment.work.grandKapital_api.models.Message;
import com.assessment.work.grandkapital.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {

    private final AuthenticationService authenticationService;

    /**
     * POST /auth : Authentication entrypoint (get jwt token)
     *
     * @param loginRequest  user credentials (required)
     * @return              Login successfully (status code 200)
     *                      or Forbidden (status code 403)
     */
    @Override
    public ResponseEntity<Message> login(LoginRequest loginRequest) {
        String token = authenticationService.login(loginRequest);
        return ResponseEntity.ok(new Message().message(token));
    }
}