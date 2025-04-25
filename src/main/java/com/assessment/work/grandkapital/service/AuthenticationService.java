package com.assessment.work.grandkapital.service;

import com.assessment.work.grandkapital.model.dto.LoginRequest;

public interface AuthenticationService {

    String login(LoginRequest loginRequest);
}