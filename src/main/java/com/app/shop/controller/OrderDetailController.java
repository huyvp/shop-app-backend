package com.app.shop.controller;

import com.app.shop.dto.request.orderDetail.OrderDetailReq;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.impl.OrderDetailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order_details")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<Object> createOrderDetail(@Valid @RequestBody OrderDetailReq orderDetailReq) {
        return ResponseHandler.execute(
                orderDetailService.create(orderDetailReq)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderDetail(@Valid @PathVariable("id") int id) {
        return ResponseHandler.execute(
                orderDetailService.getById(id)
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Object> getOrderDetailByOrderId(@Valid @PathVariable("orderId") Long orderId) {
        return ResponseHandler.execute(
                orderDetailService.getByOrderId(orderId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrderDetail(@Valid @PathVariable("id") Long id,
                                                    @RequestBody OrderDetailReq orderDetailReq) {
        return ResponseHandler.execute(
                orderDetailService.update(id, orderDetailReq)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrderDetail(@Valid @PathVariable("id") Long id) {
        orderDetailService.delete(id);
        return ResponseHandler.execute();
    }
}
