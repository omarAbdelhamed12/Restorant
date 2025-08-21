package com.spring.boot.service;

import com.spring.boot.dto.UserDto;

public interface UserService {

    UserDto getUserByName(String name);
    UserDto createUser(UserDto userDto);
}
