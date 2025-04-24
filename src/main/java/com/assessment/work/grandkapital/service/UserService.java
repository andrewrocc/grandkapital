package com.assessment.work.grandkapital.service;

import com.assessment.work.grandKapital_api.models.Page;
import com.assessment.work.grandkapital.model.entity.UserEntity;

import java.time.LocalDate;

public interface UserService {

    UserEntity getUserById(Long userId);

    Page getPage(Integer pageNumber, Integer pageSize, LocalDate dateOfBirth, String phone, String name, String email);
}