package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.boot.model.Category;
import com.spring.boot.model.User;
import com.spring.boot.vm.CategoryVm;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    @NotEmpty(message = "product.name.required")
    private String name;
    @NotEmpty(message ="product.imagePath.required")
    private String imagePath;
    @NotEmpty(message ="product.description.required")
    private String description;
    @DecimalMin(value = "30.0", message = "product.price.min")
    @DecimalMax(value = "5000.0", message ="product.price.max")
    private float price;

    private Long userId;


    @NotNull(message = "product.category.required")
    private CategoryVm category;
}
