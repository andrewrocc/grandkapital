package com.assessment.work.grandkapital.repository;

import com.assessment.work.grandkapital.model.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

    boolean existsByPhone(String phone);

    List<PhoneEntity> findByUserId(Long userId);

    Optional<PhoneEntity> findByPhoneAndUserId(String phone, Long userId);
}