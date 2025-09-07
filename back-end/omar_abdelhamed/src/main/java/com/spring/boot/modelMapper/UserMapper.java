package com.spring.boot.modelMapper;

import com.spring.boot.dto.UserDto;
import com.spring.boot.model.User;
import com.spring.boot.vm.UserResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "userDetails", target = "userDetails")
    @Mapping(source = "roles",target = "roles")
    UserDto toUserDto(User user);
    @Mapping(source = "userDetails", target = "userDetails")
    User toUser(UserDto userDto);
    List<User> toUserList(List<UserDto> userDtos);
    List<UserDto> toUserDtoList(List<User> users);

    UserResponseVm toUserResponseVm(UserDto userDto);
}
