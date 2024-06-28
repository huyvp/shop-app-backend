package com.app.shop.mapper;

import com.app.shop.dto.OrderDTO;
import com.app.shop.entity.Order;
import com.app.shop.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface OrderMapper {
    Order toOrder(OrderDTO orderDTO);
    OrderResponse toOrderResponse(Order order);
    void updateOrder(OrderDTO orderDTO, @MappingTarget Order order);
}
