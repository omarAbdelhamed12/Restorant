package com.spring.boot.service.impl;


import com.spring.boot.dto.UserDto;
import com.spring.boot.exception.CustomSystemException;
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
import org.springframework.security.core.context.SecurityContextHolder;
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
        // 1- نجيب اليوزر من SecurityContext (اللي جاي من التوكن)
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepo.findById(userDto.getId())
                .orElseThrow(() -> new CustomSystemException("User.not.found"));

        // 2- نجيب المنتجات
        List<Product> products = productRepo.findAllById(requestVm.getProductsIds());
        if (products.isEmpty()) {
            throw new CustomSystemException("No.products.found");
        }

        // 3- نعمل Order جديد
        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setCode(UUID.randomUUID().toString());
        order.setTotalPrice(
                (float) products.stream().mapToDouble(Product::getPrice).sum()
        );
        order.setTotalNumber(products.size());

        // 4- نحفظ
        Order savedOrder = orderRepo.save(order);

        // 5- نرجع Response
        return OrderMapper.ORDER_MAPPER.toOrderResponseVm(savedOrder);
    }

    @Override
    public List<OrderResponseVm> getRequestOrderByUserId(Long userId) {
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> order = orderRepo.findByUserIdOrderByIdAsc(userDto.getId());
        if (order.isEmpty()) {
            throw new CustomSystemException("order.request.found");
        }
        return OrderMapper.ORDER_MAPPER.toOrderResponseVmList(order);
    }

    @Override
    public List<OrderResponseVm> getAllRequestOrder( ) {
        List<Order> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            throw new CustomSystemException("No.order.found");
        }
        return OrderMapper.ORDER_MAPPER.toOrderResponseVmList(orders);
    }
}
