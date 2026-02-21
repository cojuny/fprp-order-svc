package com.cojuny.fprp.order.svc.dto;

import com.cojuny.fprp.order.svc.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderResponse {

    private Long id;
    private String customerId;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderItemDto> items;
}