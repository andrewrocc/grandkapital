package com.assessment.work.grandkapital.service.impl;

import com.assessment.work.grandkapital.model.dto.Page;
import com.assessment.work.grandkapital.exception.GrandKapitalException;
import com.assessment.work.grandkapital.mapper.UserMapper;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import com.assessment.work.grandkapital.repository.UserRepository;
import com.assessment.work.grandkapital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Cacheable(value = "user", key = "#userId", cacheManager = "users")
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new GrandKapitalException("User not found"));
    }

    @Override
    public Page getPage(Integer pageNumber, Integer pageSize, LocalDate dateOfBirth, String phone, String name, String email) {
        var page = userRepository.findByParams(dateOfBirth, phone, name, email, PageRequest.of(pageNumber, pageSize));

        Page pageToReturn = new Page()
                .number(page.getNumber())
                .total(page.getTotalPages())
                .elementsTotal((long) page.getContent().size());       // getTotalElements()
        page.getContent().stream().map(userMapper::mapToDto).forEach(pageToReturn::addElementsItem);

        return pageToReturn;
    }

    @Override
    public boolean doesUserExist(Long userId) {
        return userRepository.existsById(userId);
    }
}
