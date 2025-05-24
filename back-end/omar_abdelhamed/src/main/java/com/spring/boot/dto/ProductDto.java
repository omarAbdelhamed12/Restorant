package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.boot.model.Category;
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
    @NotEmpty(message = "Product name is required")
    private String name;
    @NotEmpty(message ="Image path is required")
    private String imagePath;
    @NotEmpty(message ="Description is required")
    private String description;
    @DecimalMin(value = "100.0", message = "Price must be at least 100")
    @DecimalMax(value = "5000.0", message ="Price must be at most 5000")
    private float price;


    @NotNull(message = "Category is required")
    private Category category;
}
