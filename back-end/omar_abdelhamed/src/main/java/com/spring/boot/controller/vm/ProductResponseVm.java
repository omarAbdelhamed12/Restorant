package com.spring.boot.controller.vm;

import com.spring.boot.dto.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(
        name = "Product Response Vm",
        description = "Product Response of {products, size} "
)
public class ProductResponseVm {

    @Schema(
            description = "return list of products"
    )
    private List<ProductDto>  products;
    @Schema(
            description = "return size of product"
    )
    private  Long size;
}
