package com.assessment.work.grandkapital.service.impl;

import com.assessment.work.grandkapital.model.dto.Email;
import com.assessment.work.grandkapital.exception.GrandKapitalException;
import com.assessment.work.grandkapital.mapper.EmailMapper;
import com.assessment.work.grandkapital.model.entity.EmailEntity;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.repository.EmailRepository;
import com.assessment.work.grandkapital.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;

    @Override
    public List<Email> addEmail(Long userId, String email) {
        checkExistence(email);

        EmailEntity newEmail = new EmailEntity()
                .setUser(new UserEntity().setId(userId))
                .setEmail(email);

        emailRepository.save(newEmail);
        return emailRepository.findByUserId(userId).stream().map(emailMapper::mapToDto).toList();
    }

    @Override
    public void changeEmail(Long userId, String oldEmail, String newEmail) {
        checkExistence(newEmail);

        EmailEntity emailEntity = getEmailBy(userId, oldEmail);
        emailEntity.setEmail(newEmail);
        emailRepository.save(emailEntity);
    }

    @Override
    public void removeEmail(Long userId, String email) {
        EmailEntity entity = getEmailBy(userId, email);
        emailRepository.delete(entity);
    }

    private EmailEntity getEmailBy(Long userId, String email) {
        return emailRepository.findByEmailAndUserId(email, userId).orElseThrow(() -> new GrandKapitalException("Email not found"));
    }

    private void checkExistence(String email) {
        if (emailRepository.existsByEmail(email)) {
            throw new GrandKapitalException("Email already exists, choose another one");
        }
    }
}
