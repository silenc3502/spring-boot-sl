package com.example.springspeciallecture.orders.repository;

import com.example.springspeciallecture.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
