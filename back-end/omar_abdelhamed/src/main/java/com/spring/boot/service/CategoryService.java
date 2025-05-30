package com.spring.boot.service;


import com.spring.boot.dto.CategoryDto;
import jakarta.transaction.SystemException;

import java.util.List;

public interface CategoryService  {

    List<CategoryDto> findAllCategoryDto();
    CategoryDto createCategory(CategoryDto categoryDto) ;
    List<CategoryDto> saveListOfCategoryDto(List<CategoryDto> categoryDtoList) ;
    CategoryDto updateCategory(CategoryDto categoryDto) ;
    List<CategoryDto> updateListOfCategory(List<CategoryDto> categoryDtoList) ;
   boolean deleteCategoryById(Long id) throws SystemException ;
   boolean deleteListOfCategoryDtoByListOfId(List<Long> idList)throws SystemException;


}
