package com.spring.boot.service.impl;


import com.spring.boot.dto.CategoryDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.model.Category;
import com.spring.boot.modelMapper.CategoryMapper;
import com.spring.boot.repo.CategoryRepo;
import com.spring.boot.service.CategoryService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryDto> findAllCategoryDto() {
        List<Category> categories= categoryRepo.findAll();
        return extractCategory(categories);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws SystemException {
        if (Objects.nonNull(categoryDto.getId())) {
            throw new CustomSystemException("error.id.invalid");
        }
        Optional<Category> category = categoryRepo.findByName(categoryDto.getName());
        if (category.isPresent()) {
            throw new CustomSystemException("error.categoryName.found");
        }
        Category category1 = CategoryMapper.CATEGORY_MAPPER.toCategory(categoryDto);
        categoryRepo.save(category1);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> saveListOfCategoryDto(List<CategoryDto> categoryDtoList) throws SystemException {
        if (Objects.nonNull(categoryDtoList.get(0).getId())) {
            throw new SystemException("error.id.invalid");
        }
        Optional<Category> category = categoryRepo.findByName(categoryDtoList.get(0).getName());
        if (category.isPresent()) {
            throw new SystemException("error.categoryName.unfound");
        }
        Category category1 = CategoryMapper.CATEGORY_MAPPER.toCategory(categoryDtoList.get(0));
        categoryRepo.save(category1);

        return categoryDtoList;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) throws SystemException {
        if (Objects.isNull(categoryDto.getId())) {
            throw new SystemException("error.id.valid");
        }
        Optional<Category> category = categoryRepo.findById(categoryDto.getId());
        if (category.isEmpty()) {
            throw new SystemException("error.category.notfound");
        }
        Category category1 = CategoryMapper.CATEGORY_MAPPER.toCategory(categoryDto);
        categoryRepo.save(category1);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> updateListOfCategory(List<CategoryDto> categoryDtoList) throws SystemException {
        List<CategoryDto> updatedList = new ArrayList<>();

        for (CategoryDto dto : categoryDtoList) {
            if (dto.getId() == null) {
                throw new SystemException("error.id.invalid");
            }
            Optional<Category> existingCategory = categoryRepo.findById(dto.getId());
            if (existingCategory.isEmpty()) {
                throw new SystemException("error.category.notfound");
            }

            Category updatedCategory = CategoryMapper.CATEGORY_MAPPER.toCategory(dto);
            categoryRepo.save(updatedCategory);
            updatedList.add(dto); // You can map back to DTO if needed
        }

        return updatedList;
    }

    @Override
    public boolean deleteCategoryById(Long id) throws SystemException {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isEmpty()) {
            return false;
        }
        categoryRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteListOfCategoryDtoByListOfId(List<Long> idList) throws SystemException {
        List<Long> notFoundIds = new ArrayList<>();
        for (Long id : idList) {
            if (categoryRepo.existsById(id)) {
                categoryRepo.deleteById(id);
            } else {
                notFoundIds.add(id);
            }
        }
        if (!notFoundIds.isEmpty()) {
            throw new SystemException("error.category.notfound");
        }

        return true;
    }



    private static List<CategoryDto> extractCategory(List<Category> categories) {
        List<CategoryDto> categoryDtos=new ArrayList<>();
        for (Category category : categories) {
            CategoryDto categoryDto= CategoryMapper.CATEGORY_MAPPER.toCategoryDto(category);
        categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }
}
