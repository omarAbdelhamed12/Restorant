package com.spring.boot.config;

import com.spring.boot.dto.UserDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        UserDto userDto =userService.getUserByName(username);
        if (!userDto.getPassword().equals(password)) {
            throw new CustomSystemException("Wrong.password");
        }

        List<GrantedAuthority> roles = userDto.getRoles().stream().map(rolesDto ->
                new SimpleGrantedAuthority( "ROLE_" + rolesDto.getCode())).collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(username, password,roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
