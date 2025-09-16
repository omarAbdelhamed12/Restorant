package com.spring.boot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetailsDto {
    private Long id;
    @NotEmpty(message = "productDetails.ingredients.notEmpty")
    private String ingredients;   // المكونات

    @Min(value = 1, message = "productDetails.calories.min")
    @Max(value = 9000 , message = "productDetails.calories.max")
    private int calories;         // السعرات الحرارية

    @Positive(message = "productDetails.prepTime.positive")
    @Max(value = 300 , message = "productDetails.prepTime.max")
    private int preparationTime;  // وقت التحضير بالدقايق

    private Long productId;
}
