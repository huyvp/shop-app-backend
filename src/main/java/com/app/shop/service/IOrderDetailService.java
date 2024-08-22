package com.app.shop.service;

import com.app.shop.dto.orderDetail.OrderDetailDTO;
import com.app.shop.response.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailResponse create(OrderDetailDTO orderDetailDTO);

    OrderDetailResponse update(long id, OrderDetailDTO orderDetailDTO);

    OrderDetailResponse getById(long id);

    List<OrderDetailResponse> getByOrderId(long orderId);

    void delete(long id);

}
