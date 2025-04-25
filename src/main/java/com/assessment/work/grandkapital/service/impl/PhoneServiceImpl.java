package com.assessment.work.grandkapital.service.impl;

import com.assessment.work.grandkapital.model.dto.Phone;
import com.assessment.work.grandkapital.exception.GrandKapitalException;
import com.assessment.work.grandkapital.mapper.PhoneMapper;
import com.assessment.work.grandkapital.model.entity.PhoneEntity;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.repository.PhoneRepository;
import com.assessment.work.grandkapital.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;

    @Override
    public List<Phone> addPhone(Long userId, String phone) {
        checkExistence(phone);

        PhoneEntity newPhone = new PhoneEntity()
                .setUser(new UserEntity().setId(userId))
                .setPhone(phone);

        phoneRepository.save(newPhone);
        return phoneRepository.findByUserId(userId).stream().map(phoneMapper::mapToDto).toList();
    }

    @Override
    public void changePhone(Long userId, String oldPhone, String newPhone) {
        checkExistence(newPhone);

        PhoneEntity phoneEntity = getPhoneBy(userId, oldPhone);
        phoneEntity.setPhone(newPhone);
        phoneRepository.save(phoneEntity);
    }

    @Override
    public void removePhone(Long userId, String phone) {
        PhoneEntity entity = getPhoneBy(userId, phone);
        phoneRepository.delete(entity);
    }

    private PhoneEntity getPhoneBy(Long userId, String phone) {
        return phoneRepository.findByPhoneAndUserId(phone, userId).orElseThrow(() -> new GrandKapitalException("Phone not found"));
    }

    private void checkExistence(String phone) {
        if (phoneRepository.existsByPhone(phone)) {
            throw new GrandKapitalException("Phone already exists, choose another one");
        }
    }
}