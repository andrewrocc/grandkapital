package com.assessment.work.grandkapital.service;

import com.assessment.work.grandKapital_api.models.LoginRequest;

public interface AuthenticationService {

    String login(LoginRequest loginRequest);
}