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
import java.util.stream.Collectors;

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
        userResponseVm.setRoles(getUserRole(userDto));
        return userResponseVm;
    }



    @Override
    public UserResponseVm register(UserDto userDto) {
        userDto.setRoles(List.of(roleService.getRoleByName(RoleCode.USER.toString())));
       UserDto createUser   = userService.createUser(userDto);
        UserResponseVm userResponseVm = UserMapper.USER_MAPPER.toUserResponseVm(userDto);
        userResponseVm.setToken(tokenHandler.createToken(createUser));
        userResponseVm.setRoles(getUserRole(userDto));
        return userResponseVm;
    }

    private static List<String> getUserRole(UserDto userDto) {
        return userDto.getRoles().stream().map(roleDto -> roleDto.getCode()).collect(Collectors.toList());
    }
}
