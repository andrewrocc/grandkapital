package com.assessment.work.grandkapital.repository;

import com.assessment.work.grandkapital.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> { }