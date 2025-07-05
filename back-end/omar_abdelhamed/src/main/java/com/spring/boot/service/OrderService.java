package com.spring.boot.service;

import com.spring.boot.dto.OrderDto;

public interface OrderService {

    OrderDto requestOrder(OrderDto orderDto);
}
