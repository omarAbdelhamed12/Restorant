package com.spring.boot.service.impl;


import com.spring.boot.model.Order;
import com.spring.boot.model.Product;
import com.spring.boot.model.User;
import com.spring.boot.modelMapper.OrderMapper;
import com.spring.boot.repo.OrderRepo;
import com.spring.boot.repo.ProductRepo;
import com.spring.boot.repo.UserRepo;
import com.spring.boot.service.OrderService;
import com.spring.boot.vm.OrderRequestVm;
import com.spring.boot.vm.OrderResponseVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {


        @Autowired
        private OrderRepo orderRepo;

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private ProductRepo productRepo;



        @Override
        public OrderResponseVm requestOrder(OrderRequestVm requestVm) {
            // 1- نجيب اليوزر
            User user = userRepo.findById(requestVm.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // 2- نجيب المنتجات
            List<Product> products = productRepo.findAllById(requestVm.getProductsIds());
            if (products.isEmpty()) {
                throw new RuntimeException("No products found with given IDs");
            }

            // 3- نحول الـ RequestVm إلى Order Entity
            Order order =  OrderMapper.ORDER_MAPPER.toOrder(requestVm);

            // 4- نكمل البيانات اللي MapStruct مش هيعرف يملأها
            order.setUser(user);
            order.setProducts(products);
            order.setCode(UUID.randomUUID().toString());

            // 5- نحفظ
            Order savedOrder = orderRepo.save(order);

            // 6- نحوله لـ ResponseVm
            return  OrderMapper.ORDER_MAPPER.toOrderResponseVm(savedOrder);
        }
    }




