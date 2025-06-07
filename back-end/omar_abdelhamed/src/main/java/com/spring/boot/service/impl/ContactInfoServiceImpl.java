package com.spring.boot.service.impl;

import com.spring.boot.dto.ContactInfoDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.model.ContactInfo;
import com.spring.boot.modelMapper.ContactInfoMapper;
import com.spring.boot.repo.ContactInfoRepo;
import com.spring.boot.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ContactInfoServiceImpl implements ContactInfoService  {

    @Autowired
    private ContactInfoRepo contactInfoRepo;

    @Override
    public ContactInfoDto savecContactInfo(ContactInfoDto contactInfoDto) {
        if(Objects.nonNull(contactInfoDto.getId())){
            throw new CustomSystemException("contact.id.empty");
        }
      ContactInfo contactInfo = ContactInfoMapper.CONTACT_INFO_MAPPER.toContactInfo(contactInfoDto);
      contactInfoRepo.save(contactInfo);
        return contactInfoDto;
    }
}
