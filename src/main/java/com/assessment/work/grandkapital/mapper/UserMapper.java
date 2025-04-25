package com.assessment.work.grandkapital.mapper;

import com.assessment.work.grandkapital.model.dto.Account;
import com.assessment.work.grandkapital.model.dto.User;
import com.assessment.work.grandkapital.model.entity.EmailEntity;
import com.assessment.work.grandkapital.model.entity.PhoneEntity;
import com.assessment.work.grandkapital.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "emails", source = ".", qualifiedByName = "mapEmails")
    @Mapping(target = "phones", source = ".", qualifiedByName = "mapPhones")
    @Mapping(target = "account", source = ".", qualifiedByName = "mapAccount")
    User mapToDto(UserEntity source);

    @Named("mapEmails")
    default List<String> mapEmails(UserEntity source) {
        return source.getEmails().stream().map(EmailEntity::getEmail).toList();
    }

    @Named("mapPhones")
    default List<String> mapPhones(UserEntity source) {
        return source.getPhones().stream().map(PhoneEntity::getPhone).toList();
    }

    @Named("mapAccount")
    default Account mapAccount(UserEntity source) {
        return new Account().id(source.getAccount().getId()).balance(source.getAccount().getBalance());
    }
}