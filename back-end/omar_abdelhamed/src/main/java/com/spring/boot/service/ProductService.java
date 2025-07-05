package com.spring.boot.service;


import com.spring.boot.controller.vm.ProductResponseVm;
import com.spring.boot.dto.CategoryDto;
import com.spring.boot.dto.ProductDto;
import com.spring.boot.model.Product;
import jakarta.transaction.SystemException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {


    ProductResponseVm findAllProductDto(int pageNumber, int pageSize);
    ProductResponseVm getProductsByCategoryId(Long categoryId , int pageNumber, int pageSize) ;
    ProductDto createProduct(ProductDto productDto) ;
    List<ProductDto> saveListOfProduct(List<ProductDto> productDtoList)  ;
    ProductDto updateProduct(ProductDto productDto) ;
    List<ProductDto> updateListOfProduct(List<ProductDto> productDtoList)  ;
    boolean deleteProductById(Long id) throws SystemException;
    boolean deleteListOfProductByIdList(List<Long> idList) throws SystemException ;

    ProductResponseVm  searchProductDto(String searchValue , int pageNumber, int pageSize) ;


    ProductResponseVm searchByCategoryIdAndKeyWord(Long categoryId, String searchValue, int pageNumber, int pageSize);
}
