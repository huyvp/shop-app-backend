package com.app.shop.controller;

import com.app.shop.dto.order.OrderDetailDTO;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailController {
    @PostMapping
    public String createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        return "Create order detail";
    }

    @GetMapping("/{id}")
    public String getOrderDetail(@Valid @PathVariable("id") Long id) {
        return "Get order detail with id";
    }

    @GetMapping("/order/{orderId}")
    public String getOrderDetails(@Valid @PathVariable("orderId") Long orderId) {
        return "Get list order details of a order";
    }

    @PutMapping("/{id}")
    public String updateOrderDetail(@Valid @PathVariable("id") Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
        return "Update order detail";
    }

    @DeleteMapping("/{id}")
    public String deleteOrderDetail(@Valid @PathVariable("id") Long id) {
        return "Delete order detail";
    }
}
