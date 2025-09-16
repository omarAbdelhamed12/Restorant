package com.spring.boot.service;

import com.spring.boot.dto.ProductDetailsDto;

public interface ProductDetailsService {
    ProductDetailsDto addProductDetails(ProductDetailsDto productDetailsDto);
    ProductDetailsDto getProductDetailsByProductId(Long productId);
}
