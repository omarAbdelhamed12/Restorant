package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private Long id;
    @NotEmpty(message = "")
    private String code;
    @NotEmpty(message = "")
    private float totalPrice;
    @NotNull(message = "")
    private Long totalNumber;

    private List<Long> productIds;
    private Long userId;

}
