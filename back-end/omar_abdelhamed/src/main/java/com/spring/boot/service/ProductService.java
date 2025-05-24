package com.spring.boot.service;


import com.spring.boot.dto.CategoryDto;
import com.spring.boot.dto.ProductDto;
import com.spring.boot.model.Product;
import jakarta.transaction.SystemException;

import java.util.List;

public interface ProductService {


    List<ProductDto> getProductsByCategoryId(Long categoryId) throws SystemException;
    ProductDto createProduct(ProductDto productDto) throws SystemException;
    List<ProductDto> saveListOfProduct(List<ProductDto> productDtoList) throws SystemException;
    ProductDto updateProduct(ProductDto productDto) throws SystemException;
    List<ProductDto> updateListOfProduct(List<ProductDto> productDtoList) throws SystemException;
    boolean deleteProductById(Long id) throws SystemException;
    boolean deleteListOfProductByIdList(List<Long> idList) throws SystemException;


}
