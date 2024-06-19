package com.app.shop.mapper;

import com.app.shop.dto.OrderDTO;
import com.app.shop.models.Order;
import com.app.shop.response.OrderResponse;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Order toOrder(OrderDTO orderDTO);
    OrderResponse toOrderResponse(Order order);
}
