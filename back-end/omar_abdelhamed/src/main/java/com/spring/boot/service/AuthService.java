package com.spring.boot.service;

import com.spring.boot.dto.UserDto;
import com.spring.boot.vm.UserRequestVm;
import com.spring.boot.vm.UserResponseVm;

public interface AuthService {
    UserResponseVm login(UserRequestVm userRequestVm);
    UserResponseVm register(UserDto userDto);
}
