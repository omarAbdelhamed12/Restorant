package com.spring.boot.service;

import com.spring.boot.dto.ContactInfoDto;
import com.spring.boot.model.ContactInfo;

public interface ContactInfoService {
    ContactInfoDto savecContactInfo(ContactInfoDto contactInfoDto);
}
