package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.boot.model.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Long id;
    @NotEmpty(message = "invalid.user.name")
    private String name;
    @NotEmpty(message ="Category logo must not be empty" )
    private String logo;
    @NotEmpty(message = "Category logo must not be empty")
    private String flag;

//    private List<Product> products;

}
