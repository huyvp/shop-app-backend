package com.app.shop.service.impl;

import com.app.shop.dto.OrderDTO;
import com.app.shop.entity.Order;
import com.app.shop.entity.User;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.OrderMapper;
import com.app.shop.repo.OrderRepo;
import com.app.shop.repo.UserRepo;
import com.app.shop.response.OrderResponse;
import com.app.shop.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

import static com.app.shop.constant.Constants.ORDER_STATUS.PENDING;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    UserRepo userRepo;
    OrderMapper orderMapper;
    OrderRepo orderRepo;

    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) {
        User user = userRepo.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        Order order = orderMapper.toOrder(orderDTO);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setActive(true);
        order.setStatus(PENDING);
        Order savedOrder = orderRepo.save(order);
        Date shippingDate = orderDTO.getShippingDate();
        if (shippingDate == null || shippingDate.before(new Date())){
            throw new ShopAppException(ErrorCode.ORDER_3003);
        }
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public Page<OrderResponse> getAllOrder(PageRequest pageRequest) {
        return orderRepo.findAll(pageRequest)
                .map(orderMapper::toOrderResponse);
    }

    @Override
    public Page<OrderResponse> getOrderByUserId(long userId, PageRequest pageRequest) {
        userRepo.findById(userId)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        return orderRepo.findByUserId(userId, pageRequest)
                .map(orderMapper::toOrderResponse);
    }

    @Override
    public void updateOrder(long id, OrderDTO orderDTO) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        orderMapper.updateOrder(orderDTO, order);
        orderRepo.save(order);
    }

    @Override
    public void deleteOrder(long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        orderRepo.delete(order);
    }
}
