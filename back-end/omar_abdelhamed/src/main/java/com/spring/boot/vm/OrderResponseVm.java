package com.spring.boot.vm;

 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseVm {
    private Long userId;
    private String code;
     private double totalPrice;
     private double totalNumber;


}
