package com.app.shop.service.impl;

import com.app.shop.dto.order.OrderDetailDTO;
import com.app.shop.entity.Order;
import com.app.shop.entity.OrderDetail;
import com.app.shop.entity.Product;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.OrderDetailMapper;
import com.app.shop.repo.OrderDetailRepo;
import com.app.shop.repo.OrderRepo;
import com.app.shop.repo.ProductRepo;
import com.app.shop.service.IOrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    ProductRepo productRepo;
    OrderRepo orderRepo;
    OrderDetailRepo orderDetailRepo;
    OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetail create(OrderDetailDTO orderDetailDTO) {
        Order order = orderRepo.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        Product product = productRepo.findById(orderDetailDTO.getProductId())
                .orElseThrow(()-> new ShopAppException(ErrorCode.PRODUCT_3002));
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailDTO);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetailRepo.save(orderDetail);
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public OrderDetail update(long id, OrderDetailDTO orderDetailDTO) {
        OrderDetail savedOrderDetail = orderDetailRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_DETAIL_3002));

        return null;
    }

    @Override
    public OrderDetail getById(long id) {
        return null;
    }

    @Override
    public List<OrderDetail> getAll() {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
