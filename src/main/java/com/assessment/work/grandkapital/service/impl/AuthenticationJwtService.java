package com.assessment.work.grandkapital.service.impl;

import com.assessment.work.grandkapital.model.dto.LoginRequest;
import com.assessment.work.grandkapital.exception.GrandKapitalException;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.repository.UserRepository;
import com.assessment.work.grandkapital.service.AuthenticationService;
import com.assessment.work.grandkapital.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationJwtService implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String login(LoginRequest loginRequest) {
        Optional<UserEntity> user = userRepository.findByEmailsEmail(loginRequest.getEmail());

        if (user.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            throw new GrandKapitalException("Wrong login or password", HttpStatus.FORBIDDEN);
        }

        return jwtService.generateToke(user.get().getId());
    }
}