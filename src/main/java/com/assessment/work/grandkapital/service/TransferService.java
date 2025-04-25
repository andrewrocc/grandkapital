package com.assessment.work.grandkapital.service;

import com.assessment.work.grandkapital.model.dto.TransferRequest;

public interface TransferService {

    void transfer(TransferRequest transferRequest, Long fromUserId);
}