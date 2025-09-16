package com.spring.boot.service;


import com.spring.boot.vm.OrderRequestVm;
import com.spring.boot.vm.OrderResponseVm;

import java.util.List;

public interface OrderService {
    OrderResponseVm requestOrder(OrderRequestVm orderRequestVm);
    List<OrderResponseVm> getRequestOrderByUserId(Long userId);
    List<OrderResponseVm> getAllRequestOrder();
}
