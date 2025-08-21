package com.spring.boot.dto;

import com.spring.boot.model.ContactInfo;
import com.spring.boot.model.Order;
import com.spring.boot.model.Role;
import com.spring.boot.model.UserDetails;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDto {

    private Long id;
    @NotEmpty(message = "not_empty.username")
    @Size(min = 7, message = "size.username")
    private String userName;
  //  @Pattern(
  //          regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{7,}$",
  //          message = "error.password"
  //  )
    private String password;
    @Valid
    private UserDetailsDto userDetails;

    private List<RoleDto> roles;

    //private List<ContactInfo> contactInfos;

   // private Order order;
}
