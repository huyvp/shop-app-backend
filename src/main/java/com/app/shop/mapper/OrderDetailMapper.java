package com.app.shop.mapper;

import com.app.shop.dto.order.OrderDetailDTO;
import com.app.shop.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface OrderDetailMapper {
    OrderDetail toOrderDetail(OrderDetailDTO orderDetail);

    void updateOrderDetail(OrderDetailDTO orderDetailDTO, @MappingTarget OrderDetail orderDetail);
}
