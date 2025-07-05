package com.spring.boot.modelMapper;

import com.spring.boot.dto.OrderDto;
import com.spring.boot.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);
    OrderDto requestOrder(OrderDto orderDto);
    Order convertOrderDtoToOrder(OrderDto orderDto);
}
