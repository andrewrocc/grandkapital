package com.assessment.work.grandkapital.repository;

import com.assessment.work.grandkapital.model.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

    boolean existsByEmail(String email);

    List<EmailEntity> findByUserId(Long userId);

    Optional<EmailEntity> findByEmailAndUserId(String email, Long userId);
}