package com.assessment.work.grandkapital.service;

import com.assessment.work.grandKapital_api.models.TransferRequest;

public interface TransferService {

    void transfer(TransferRequest transferRequest, Long fromUserId);
}