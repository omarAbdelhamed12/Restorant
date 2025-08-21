package com.spring.boot.vm;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequestVm {
    private Long Id;
    private Long userId;
    @NotNull(message = "error.total_price.not_empty")
    private double totalPrice;
    @NotNull(message = "error.total_number.not_empty")
    private double totalNumber;
    @NotEmpty(message = "error.products_ids.not_empty")
    List<Long> productsIds;

}
