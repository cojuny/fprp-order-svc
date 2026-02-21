package com.cojuny.fprp.order.svc.service;

import com.cojuny.fprp.order.svc.domain.Order;
import com.cojuny.fprp.order.svc.domain.OrderItem;
import com.cojuny.fprp.order.svc.domain.OrderStatus;
import com.cojuny.fprp.order.svc.dto.CreateOrderRequest;
import com.cojuny.fprp.order.svc.dto.OrderItemDto;
import com.cojuny.fprp.order.svc.dto.OrderResponse;
import com.cojuny.fprp.order.svc.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        List<OrderItem> items = request.getItems().stream()
                .map(dto -> OrderItem.builder()
                        .order(order)
                        .productId(dto.getProductId())
                        .quantity(dto.getQuantity())
                        .build())
                .toList();

        order.setItems(items);

        Order saved = orderRepository.save(order);

        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders(String customerId) {

        List<Order> orders = (customerId == null)
                ? orderRepository.findAll()
                : orderRepository.findByCustomerId(customerId);

        return orders.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {

        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream()
                        .map(i -> {
                            OrderItemDto dto = new OrderItemDto();
                            dto.setProductId(i.getProductId());
                            dto.setQuantity(i.getQuantity());
                            return dto;
                        })
                        .toList())
                .build();
    }
}