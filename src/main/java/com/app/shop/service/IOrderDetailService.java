package com.app.shop.service;

import com.app.shop.dto.order.OrderDetailDTO;
import com.app.shop.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail create(OrderDetailDTO orderDetailDTO);

    OrderDetail update(long id, OrderDetailDTO orderDetailDTO);

    OrderDetail getById(long id);

    List<OrderDetail> getAll();

    void delete(long id);

}
