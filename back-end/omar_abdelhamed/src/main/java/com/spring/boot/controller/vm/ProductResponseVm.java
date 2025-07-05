package com.spring.boot.controller.vm;

import com.spring.boot.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseVm {

    private List<ProductDto>  products;
    private  Long size;
}
