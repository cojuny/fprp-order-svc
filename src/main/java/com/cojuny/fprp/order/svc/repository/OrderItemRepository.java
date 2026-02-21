package com.cojuny.fprp.order.svc.repository;

import com.cojuny.fprp.order.svc.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}