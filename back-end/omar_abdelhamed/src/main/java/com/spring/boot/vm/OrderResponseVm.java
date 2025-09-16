package com.spring.boot.vm;

 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseVm {
    private Long userId;
    private String code;
     private double totalPrice;
     private double totalNumber;
    private List<String> productNames;
    private List<String> imagePath;
    private List<Float> price;


}
