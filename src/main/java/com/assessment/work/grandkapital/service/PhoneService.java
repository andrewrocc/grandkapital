package com.assessment.work.grandkapital.service;

import com.assessment.work.grandkapital.model.dto.Phone;

import java.util.List;

public interface PhoneService {

    List<Phone> addPhone(Long userId, String phone);

    void changePhone(Long userId, String oldPhone, String newPhone);

    void removePhone(Long userId, String phone);
}
