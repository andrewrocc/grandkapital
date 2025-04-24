package com.assessment.work.grandkapital.service;

import com.assessment.work.grandKapital_api.models.Email;
import java.util.List;

public interface EmailService {

    List<Email> addEmail(Long userId, String email);

    void changeEmail(Long userId, String oldEmail, String newEmail);

    void removeEmail(Long userId, String email);
}