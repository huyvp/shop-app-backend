package com.app.shop.mapper;

import com.app.shop.dto.OrderDTO;
import com.app.shop.models.Order;
import com.app.shop.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface OrderMapper {
    Order toOrder(OrderDTO orderDTO);

    @Mapping(source = "user.id", target = "userId")
    OrderResponse toOrderResponse(Order order);

    void updateOrder(OrderDTO orderDTO, @MappingTarget Order order);
}
