package com.spring.boot.modelMapper;

import com.spring.boot.model.Order;
import com.spring.boot.model.Product;
import com.spring.boot.vm.OrderProductProjection;
import com.spring.boot.vm.OrderRequestVm;
import com.spring.boot.vm.OrderResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "productNames", expression = "java(getProductNames(order.getProducts()))")
    @Mapping(target = "imagePath", expression = "java(getProductImage(order.getProducts()))")
    @Mapping(target = "price", expression = "java(getProductPrices(order.getProducts()))")
    OrderResponseVm toOrderResponseVm(Order order);
    Order  toOrder(OrderRequestVm orderRequestVm);
     List<OrderResponseVm> toOrderResponseVmList(List<Order> orders);

     default List<String> getProductNames(List<Product> products) {
        if (products == null) return new ArrayList<>();
        return products.stream().map(Product::getName).collect(Collectors.toList());
    }
    default List<String> getProductImage(List<Product> products) {
        if (products == null) return new ArrayList<>();
        return products.stream().map(Product::getImagePath).collect(Collectors.toList());
    }
    default List<Float> getProductPrices(List<Product> products) {
        if (products == null) return new ArrayList<>();
        return products.stream().map(Product::getPrice).collect(Collectors.toList());
    }
}
