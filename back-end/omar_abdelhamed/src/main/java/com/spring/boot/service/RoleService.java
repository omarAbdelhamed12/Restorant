package com.spring.boot.service;

import com.spring.boot.dto.RoleDto;
import com.spring.boot.model.Role;

import java.util.List;

public interface RoleService {

    RoleDto getRoleByName(String roleName);
    List<RoleDto> updateRole(List<Role> roles);
}
