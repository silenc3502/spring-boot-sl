package com.example.springspeciallecture.payments.repository;

import com.example.springspeciallecture.payments.entity.PaymentStatus;
import com.example.springspeciallecture.payments.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
    List<Payments> findByStatusAndRequestedAtBetween(PaymentStatus status, LocalDateTime start, LocalDateTime end);
}
