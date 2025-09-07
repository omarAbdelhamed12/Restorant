package com.spring.boot.controller;

import com.spring.boot.service.OrderService;
import com.spring.boot.vm.OrderRequestVm;
import com.spring.boot.vm.OrderResponseVm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/order")
//@CrossOrigin("http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/request")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<OrderResponseVm> requestOrder(@RequestBody @Valid OrderRequestVm orderRequestVm) {
        return ResponseEntity.created(URI.create("/order/")).body(orderService.requestOrder(orderRequestVm));
    }
}
