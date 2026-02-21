package com.cojuny.fprp.order.svc.controller;

import com.cojuny.fprp.order.svc.dto.CreateOrderRequest;
import com.cojuny.fprp.order.svc.dto.OrderResponse;
import com.cojuny.fprp.order.svc.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @Valid @RequestBody CreateOrderRequest request) {

        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll(
            @RequestParam(required = false) String customerId) {

        return ResponseEntity.ok(orderService.getOrders(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}