package com.spring.boot.service.impl;

import com.spring.boot.Enum.RoleCode;
import com.spring.boot.config.TokenHandler;
import com.spring.boot.dto.UserDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.modelMapper.UserMapper;
import com.spring.boot.service.AuthService;
import com.spring.boot.service.RoleService;
import com.spring.boot.service.UserService;
import com.spring.boot.vm.UserRequestVm;
import com.spring.boot.vm.UserResponseVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private RoleService roleService;
    @Override
    public UserResponseVm login(UserRequestVm userRequestVm) {
       UserDto userDto = userService.getUserByName(userRequestVm.getUserName());
       if(Objects.isNull(userDto)) {
           throw new CustomSystemException("User.not.found");
       }
       if (!passwordEncoder.matches(userRequestVm.getPassword(), userDto.getPassword())) {
           throw new CustomSystemException("Wrong.password");
       }
       UserResponseVm userResponseVm = UserMapper.USER_MAPPER.toUserResponseVm(userDto);
       userResponseVm.setToken(tokenHandler.createToken(userDto));
        return userResponseVm;
    }

    @Override
    public UserResponseVm register(UserDto userDto) {
        userDto.setRoles(List.of(roleService.getRoleByName(RoleCode.USER.toString())));
       UserDto createUser   = userService.createUser(userDto);
        UserResponseVm userResponseVm = UserMapper.USER_MAPPER.toUserResponseVm(userDto);
        userResponseVm.setToken(tokenHandler.createToken(createUser));
        return userResponseVm;
    }
}
