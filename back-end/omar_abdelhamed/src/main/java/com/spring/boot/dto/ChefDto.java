package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChefDto {
    private int id;
    @NotEmpty(message = "chef.name.found")
    private String name;

    @NotEmpty(message = "chef.specialty.found")
    private String specialty;

    private String logoPath;

    private String faceLink;

    private String tweLink;

    private String instaLink;
}
