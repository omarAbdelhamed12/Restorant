package com.spring.boot.service.impl;

import com.spring.boot.dto.ChefDto;
import com.spring.boot.model.Chef;
import com.spring.boot.modelMapper.ChefMapper;
import com.spring.boot.repo.ChefRepo;
import com.spring.boot.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    private ChefRepo chefRepo;


    @Override
    public List<ChefDto> getALLChefs() {
        List<Chef> chefs = chefRepo.findAll();

        List<ChefDto> chefDtos = new ArrayList<>();
        for (Chef chef : chefs) {
            ChefDto chefDto = ChefMapper.CHEF_MAPPER.toChefDto(chef);
            chefDtos.add(chefDto);
        }
        return chefDtos;
    }
}
