package com.spring.boot.modelMapper;

import com.spring.boot.dto.ProductDetailsDto;
import com.spring.boot.model.ProductDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDetailsMapper {
    ProductDetailsMapper PRODUCT_DETAILS_MAPPER = Mappers.getMapper(ProductDetailsMapper.class);
    @Mapping(source = "product.id", target = "productId")
    ProductDetailsDto toProductDetailsDto(ProductDetails productDetails);
    @Mapping(source = "productId", target = "product.id")
    ProductDetails toProductDetails(ProductDetailsDto productDetailsDto);
}
