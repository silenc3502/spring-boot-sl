package com.example.springspeciallecture.orders.repository;

import com.example.springspeciallecture.orders.entity.OrderItem;
import com.example.springspeciallecture.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrders(Orders orders);
}
