package com.cojuny.fprp.order.svc.repository;

import com.cojuny.fprp.order.svc.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(String customerId);
}