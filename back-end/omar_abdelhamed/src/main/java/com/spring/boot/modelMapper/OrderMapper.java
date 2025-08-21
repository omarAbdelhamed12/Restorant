package com.spring.boot.modelMapper;

import com.spring.boot.dto.OrderDto;
import com.spring.boot.model.Order;
import com.spring.boot.vm.OrderRequestVm;
import com.spring.boot.vm.OrderResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);
    OrderResponseVm toOrderResponseVm(Order order);
    Order  toOrder(OrderRequestVm orderRequestVm);

}
