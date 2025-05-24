package com.spring.boot.modelMapper;


import com.spring.boot.dto.CategoryDto;
import com.spring.boot.dto.ProductDto;
import com.spring.boot.model.Category;
import com.spring.boot.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;
@Mapper
public interface CategoryMapper {


    CategoryMapper CATEGORY_MAPPER= Mappers.getMapper(CategoryMapper.class);
    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtoList(List<Category> categoryList);
    List<ProductDto> toProductDtoList(List<Product> productList);


}
