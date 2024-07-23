package com.app.shop.mapper;

import com.app.shop.dto.order.OrderDTO;
import com.app.shop.entity.Order;
import com.app.shop.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface OrderMapper {
    Order toOrder(OrderDTO orderDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "id" , target = "id")
    OrderResponse toOrderResponse(Order order);

    void updateOrder(OrderDTO orderDTO, @MappingTarget Order order);
}
