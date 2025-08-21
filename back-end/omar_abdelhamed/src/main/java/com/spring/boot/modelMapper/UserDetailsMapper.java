package com.spring.boot.modelMapper;

import com.spring.boot.dto.UserDetailsDto;
import com.spring.boot.model.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserDetailsMapper {
    UserDetailsMapper USER_DETAILS_MAPPER = Mappers.getMapper(UserDetailsMapper.class);
    UserDetailsDto toUserDetailsDto(UserDetails userDetails);
    UserDetails toUserDetails(UserDetailsDto userDetailsDto);
}
