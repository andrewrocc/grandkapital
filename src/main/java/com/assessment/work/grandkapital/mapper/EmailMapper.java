package com.assessment.work.grandkapital.mapper;

import com.assessment.work.grandKapital_api.models.Email;
import com.assessment.work.grandkapital.model.entity.EmailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailMapper {

    Email mapToDto(EmailEntity source);
}