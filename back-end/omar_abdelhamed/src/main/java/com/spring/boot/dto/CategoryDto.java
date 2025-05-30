package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Long id;
    @NotEmpty(message = "invalid.user.name")
    private String name;
    @NotEmpty(message ="invalid.logo.empty" )
    private String logo;
    @NotEmpty(message = "invalid.flag.empty")
    private String flag;

//    private List<Product> products;

}

