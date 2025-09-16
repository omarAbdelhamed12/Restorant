package com.spring.boot.controller;

import com.spring.boot.dto.ProductDetailsDto;
import com.spring.boot.service.ProductDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/ProductDetails")
public class ProductDetailsController {

    @Autowired
    private ProductDetailsService productDetailsService;

    @PostMapping("/addDetail")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDetailsDto> addDetail(@RequestBody @Valid ProductDetailsDto productDetailsDto) {
        return ResponseEntity.created(URI.create("/ProductDetailsController/addDetail")).body(productDetailsService.addProductDetails(productDetailsDto));
    }
    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDetailsDto> getProductDetailsByProductId(@PathVariable("productId")  Long productId) {
        return ResponseEntity.ok(productDetailsService.getProductDetailsByProductId(productId));
    }
}
