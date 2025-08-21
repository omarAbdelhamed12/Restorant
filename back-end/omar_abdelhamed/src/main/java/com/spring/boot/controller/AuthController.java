package com.spring.boot.controller;

import com.spring.boot.dto.UserDto;
import com.spring.boot.service.AuthService;
import com.spring.boot.service.UserService;
import com.spring.boot.vm.UserRequestVm;
import com.spring.boot.vm.UserResponseVm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/Auth")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseVm> login(@RequestBody @Valid UserRequestVm userRequestVm) {
        return ResponseEntity.created(URI.create("/login")).body(authService.login(userRequestVm));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseVm> register(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.created(URI.create("/register")).body(authService.register(userDto));
    }
}
