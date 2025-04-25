package com.assessment.work.grandkapital.mapper;

import com.assessment.work.grandkapital.model.dto.Phone;
import com.assessment.work.grandkapital.model.entity.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneMapper {

    Phone mapToDto(PhoneEntity source);
}