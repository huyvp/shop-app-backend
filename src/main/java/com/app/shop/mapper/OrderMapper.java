package com.app.shop.mapper;

import com.app.shop.dto.request.order.OrderReq;
import com.app.shop.entity.Order;
import com.app.shop.dto.response.OrderResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order toOrder(OrderReq orderReq);

//    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "userResponse")
    @Mapping(target = "userResponse.roles", source = "user.roles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderResponse toOrderResponse(Order order);

    @Mapping(source = "userId", target = "user.id", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateOrder(OrderReq orderReq, @MappingTarget Order order);
}
