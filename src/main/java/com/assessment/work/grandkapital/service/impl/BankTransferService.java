package com.assessment.work.grandkapital.service.impl;

import com.assessment.work.grandkapital.model.dto.TransferRequest;
import com.assessment.work.grandkapital.exception.GrandKapitalException;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.repository.UserRepository;
import com.assessment.work.grandkapital.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BankTransferService implements TransferService {

    private final UserRepository userRepository;

    @Override
    @Transactional  //(isolation = Isolation.SERIALIZABLE)
    public void transfer(TransferRequest transferRequest, Long fromUserId) {
        if (fromUserId.equals(transferRequest.getToUserId())) {
            throw new GrandKapitalException("You can't transfer money to yourself");
        }
        if (transferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new GrandKapitalException("Amount of money must be greater than zero");
        }

        UserEntity fromUser = userRepository.findByIdPessimisticWrite(fromUserId).orElseThrow(() -> new GrandKapitalException("The sender was not found"));
        UserEntity toUser = userRepository.findByIdPessimisticWrite(transferRequest.getToUserId()).orElseThrow(() -> new GrandKapitalException("The recipient was not found"));

        if (fromUser.getAccount().getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new GrandKapitalException("Insufficient funds");
        }

        fromUser.getAccount().setBalance(fromUser.getAccount().getBalance().subtract(transferRequest.getAmount()));
        toUser.getAccount().setBalance(toUser.getAccount().getBalance().add(transferRequest.getAmount()));

        userRepository.save(fromUser);
        userRepository.save(toUser);
    }
}