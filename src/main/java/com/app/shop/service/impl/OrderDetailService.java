package com.app.shop.service.impl;

import com.app.shop.dto.request.orderDetail.OrderDetailReq;
import com.app.shop.entity.Order;
import com.app.shop.entity.OrderDetail;
import com.app.shop.entity.Product;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.OrderDetailMapper;
import com.app.shop.repo.OrderDetailRepo;
import com.app.shop.repo.OrderRepo;
import com.app.shop.repo.ProductRepo;
import com.app.shop.dto.response.OrderDetailResponse;
import com.app.shop.service.IOrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.shop.exception.ErrorCode.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    ProductRepo productRepo;
    OrderRepo orderRepo;
    OrderDetailRepo orderDetailRepo;
    OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailResponse create(OrderDetailReq orderDetailReq) {
        Order order = orderRepo.findById(orderDetailReq.getOrderId())
                .orElseThrow(() -> new ShopAppException(ORDER_3002));
        Product product = productRepo.findById(orderDetailReq.getProductId())
                .orElseThrow(() -> new ShopAppException(PRODUCT_3002));
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailReq);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        OrderDetail savedOrderDetail = orderDetailRepo.save(orderDetail);
        return orderDetailMapper.toOrderDetailResponse(savedOrderDetail);
    }

    @Override
    public OrderDetailResponse update(long id, OrderDetailReq orderDetailReq) {
        OrderDetail savedOrderDetail = orderDetailRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ORDER_DETAIL_3002));
        Order savedOrder = orderRepo.findById(orderDetailReq.getOrderId())
                .orElseThrow(() -> new ShopAppException(ORDER_3002));
        Product savedProduct = productRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(PRODUCT_3002));
        savedOrderDetail.setOrder(savedOrder);
        savedOrderDetail.setProduct(savedProduct);
        orderDetailMapper.updateOrderDetail(orderDetailReq, savedOrderDetail);
        OrderDetail orderDetail = orderDetailRepo.save(savedOrderDetail);

        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }


    @Override
    public OrderDetailResponse getById(long id) {
        OrderDetail orderDetail = orderDetailRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ORDER_DETAIL_3002));
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    @Override
    public List<OrderDetailResponse> getByOrderId(long orderId) {
        return orderDetailRepo.findByOrderId(orderId).stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .toList();
    }

    @Override
    public void delete(long id) {
        orderDetailRepo.deleteById(id);
    }
}
