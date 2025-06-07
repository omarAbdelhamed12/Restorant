package com.spring.boot.modelMapper;

import com.spring.boot.dto.ChefDto;
import com.spring.boot.model.Chef;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChefMapper {

    ChefMapper CHEF_MAPPER = Mappers.getMapper(ChefMapper.class);
    Chef toChef(ChefDto chefDto);
    ChefDto toChefDto(Chef chef);


    List<Chef> toChefDtoList(List<ChefDto> chefDtos);
    List<ChefDto> toChefList(List<Chef> chefs);
}
