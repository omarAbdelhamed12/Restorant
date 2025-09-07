package com.spring.boot.vm;

import com.spring.boot.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseVm {

    private String userName;
    private String token;
    private List<String> Roles;
}
