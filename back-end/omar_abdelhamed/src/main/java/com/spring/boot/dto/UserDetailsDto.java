package com.spring.boot.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsDto {
    private Long id;
    @Min(value = 15, message = "user.age.min")
    @Max(value = 70, message = "user.age.max")
    private Long age;
    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10,15}$",
            message = "user.phone.invalid"
    )
    private String phoneNumber;
    @Size(min = 4, max = 200, message = "user.address.size")
    private String address;


    private UserDto userDto;


}
