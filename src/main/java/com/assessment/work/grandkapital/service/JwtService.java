package com.assessment.work.grandkapital.service;

public interface JwtService {

    String generateToke(Long id);

    Long getUserId(String token);

    boolean validateToken(String token);
}