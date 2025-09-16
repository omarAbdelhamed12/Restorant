package com.spring.boot.config.filter;

import com.spring.boot.config.TokenHandler;
import com.spring.boot.dto.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("Auth")) {
            return true;
        }
        return false;
    }

    @Autowired
    private TokenHandler tokenHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")
                || path.equals("/swagger-ui.html") || path.startsWith("/webjars")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");
       if(Objects.isNull(token) || !token.startsWith("Bearer ")) {
           response.reset();
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           return;
       }
       token= token.substring(7);
       UserDto userDto = tokenHandler.validateToken(token);
       if(Objects.isNull(userDto)) {
           response.reset();
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           return;
       }

        List<GrantedAuthority> roles = userDto.getRoles().stream().map(roleDto ->
                new SimpleGrantedAuthority("ROLE_" + roleDto.getCode())).collect(Collectors.toList());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDto,userDto.getPassword(),roles);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
