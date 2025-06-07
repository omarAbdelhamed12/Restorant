package com.spring.boot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfoDto {
    private Long id;
    @NotEmpty(message = "contact.name.notempty")
    private String name;
    @Email(message = "contact.email.valid")
    @NotEmpty(message = "contact.email.notempty")
    private String email;
    @NotEmpty(message = "contact.subject.notempty")
    private String subject;
    @NotEmpty(message = "contact.message.notempty")
    private String message;
}
