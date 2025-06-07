package com.spring.boot.modelMapper;

import com.spring.boot.dto.ContactInfoDto;
import com.spring.boot.model.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactInfoMapper {

    ContactInfoMapper CONTACT_INFO_MAPPER = Mappers.getMapper(ContactInfoMapper.class);
    ContactInfo toContactInfo(ContactInfoDto contactInfoDto);
    ContactInfoDto toContactInfoDto(ContactInfo contactInfo);


    List<ContactInfo> toContactInfoList(List<ContactInfo> contactInfoList);
    List<ContactInfoDto> toContactInfoDtoList(List<ContactInfoDto> contactInfoDtoList);
}
