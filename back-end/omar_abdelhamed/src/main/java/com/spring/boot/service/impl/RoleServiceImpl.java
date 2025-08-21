package com.spring.boot.service.impl;

import com.spring.boot.dto.RoleDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.model.Role;
import com.spring.boot.modelMapper.RoleMapper;
import com.spring.boot.repo.RoleRepo;
import com.spring.boot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;


    @Override
    public RoleDto getRoleByName(String roleName) {
        Optional<Role> role = roleRepo.findByCode(roleName);
        if (!role.isPresent()) {
            throw new CustomSystemException("Role.not.found");
        }
        return RoleMapper.ROLE_MAPPER.toRoleDto(role.get());
    }

    @Override
    public List<RoleDto> updateRole(List<Role> roles) {
        return RoleMapper.ROLE_MAPPER.toRoleDtoList(roleRepo.saveAll(roles));
    }
}
