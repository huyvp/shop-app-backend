package com.app.shop.mapper;

import com.app.shop.dto.order.OrderDTO;
import com.app.shop.entity.Order;
import com.app.shop.response.OrderResponse;
import org.mapstruct.*;

@Mapper
public interface OrderMapper {

    Order toOrder(OrderDTO orderDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "id", target = "id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderResponse toOrderResponse(Order order);

    @Mapping(source = "userId", target = "user.id", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateOrder(OrderDTO orderDTO, @MappingTarget Order order);
}
