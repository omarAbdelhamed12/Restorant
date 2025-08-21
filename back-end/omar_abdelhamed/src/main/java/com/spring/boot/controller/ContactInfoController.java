package com.spring.boot.controller;

import com.spring.boot.dto.ContactInfoDto;
import com.spring.boot.service.ContactInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/contactInfo")
//@CrossOrigin("http://localhost:4200")
public class ContactInfoController {
    @Autowired
    private ContactInfoService contactInfoService;

    @PostMapping("/addContact")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ContactInfoDto> addContact(@RequestBody @Valid ContactInfoDto contactInfoDto) {
        return ResponseEntity.created(URI.create("/addContact")).body(contactInfoService.savecContactInfo(contactInfoDto));

    }
}
