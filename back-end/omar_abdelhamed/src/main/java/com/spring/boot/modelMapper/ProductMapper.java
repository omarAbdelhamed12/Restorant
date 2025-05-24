package com.spring.boot.modelMapper;

import com.spring.boot.dto.ProductDto;
import com.spring.boot.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);
    Product toProduct(ProductDto productDto);
    ProductDto toProductDto(Product product);
    List<ProductDto> toProductDtoList(List<Product> productList);
    List<Product> toProductList(List<ProductDto> productDtoList);
}
