package com.spring.boot.service.impl;

import com.spring.boot.dto.OrderDto;
import com.spring.boot.model.Order;
import com.spring.boot.model.Product;
import com.spring.boot.modelMapper.OrderMapper;
import com.spring.boot.repo.OrderRepo;
import com.spring.boot.repo.ProductRepo;
import com.spring.boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;


    @Override
    public OrderDto requestOrder(OrderDto orderDto) {
        List<Product> products = productRepo.findAllById(orderDto.getProductIds());
        Order order = new Order();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Product product : products) {
           OrderDto orderDto1= OrderMapper.ORDER_MAPPER.requestOrder(orderDto);
           orderDtos.add(orderDto1);
        }

        return null;
    }
}
