package com.spring.boot.service;


import com.spring.boot.vm.OrderRequestVm;
import com.spring.boot.vm.OrderResponseVm;
public interface OrderService {
    OrderResponseVm requestOrder(OrderRequestVm orderRequestVm);





}
