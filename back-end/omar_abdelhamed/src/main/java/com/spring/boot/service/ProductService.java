package com.spring.boot.service;


import com.spring.boot.dto.CategoryDto;
import com.spring.boot.dto.ProductDto;
import com.spring.boot.model.Product;
import jakarta.transaction.SystemException;

import java.util.List;

public interface ProductService {


    List<ProductDto> findAllProductDto();
    List<ProductDto> getProductsByCategoryId(Long categoryId) ;
    ProductDto createProduct(ProductDto productDto) ;
    List<ProductDto> saveListOfProduct(List<ProductDto> productDtoList)  ;
    ProductDto updateProduct(ProductDto productDto) ;
    List<ProductDto> updateListOfProduct(List<ProductDto> productDtoList)  ;
    boolean deleteProductById(Long id) throws SystemException;
    boolean deleteListOfProductByIdList(List<Long> idList) throws SystemException ;

   List<ProductDto>   searchProductDto(String searchValue) ;


}
