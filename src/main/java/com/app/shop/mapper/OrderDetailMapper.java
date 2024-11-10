package com.app.shop.mapper;

import com.app.shop.dto.request.orderDetail.OrderDetailReq;
import com.app.shop.entity.OrderDetail;
import com.app.shop.dto.response.OrderDetailResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDetailMapper {
    public OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    OrderDetail toOrderDetail(OrderDetailReq orderDetail);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "order.id", target = "orderId")
    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

    void updateOrderDetail(OrderDetailReq orderDetailReq, @MappingTarget OrderDetail orderDetail);
}
