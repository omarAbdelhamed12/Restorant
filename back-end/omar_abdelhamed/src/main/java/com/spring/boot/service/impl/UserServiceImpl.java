package com.spring.boot.service.impl;

import com.spring.boot.dto.UserDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.model.Role;
import com.spring.boot.model.User;
import com.spring.boot.model.UserDetails;
import com.spring.boot.modelMapper.UserDetailsMapper;
import com.spring.boot.modelMapper.UserMapper;
import com.spring.boot.repo.UserRepo;
import com.spring.boot.service.RoleService;
import com.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private @Lazy PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDto getUserByName(String name) {
        Optional<User> user = userRepo.findByUserName(name);
        if(!user.isPresent()){
            throw new CustomSystemException("User.not.found");
        }
        return UserMapper.USER_MAPPER.toUserDto(user.get());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (Objects.nonNull(userDto.getId())) {
            throw new CustomSystemException("user.id.null");
        }
        Optional<User> user = userRepo.findByUserName(userDto.getUserName());
        if(user.isPresent()){
            throw new CustomSystemException("User.found");
        }
       User user1 = UserMapper.USER_MAPPER.toUser(userDto);
        user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        if (user1.getUserDetails() != null) {
//            user1.getUserDetails().setUser(user1);
//        }
       if(userDto.getUserDetails() != null){
           UserDetails userDetails = UserDetailsMapper.USER_DETAILS_MAPPER.toUserDetails(userDto.getUserDetails());
           user1.setUserDetails(userDetails);
           userDetails.setUser(user1);
       }
        user1= userRepo.save(user1);
        List<Role> roles =  user1.getRoles();
        final User finalUser = user1;
         roles.forEach(role -> {
             List<User> users = role.getUsers();
             if (users == null){
                 users = new ArrayList<>();
             }
             users.add(finalUser);
             role.setUsers(users);

         });
         roleService.updateRole(roles);
        return UserMapper.USER_MAPPER.toUserDto(user1);
    }
}
