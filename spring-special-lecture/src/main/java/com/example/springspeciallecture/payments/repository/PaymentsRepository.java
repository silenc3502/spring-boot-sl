package com.example.springspeciallecture.payments.repository;

import com.example.springspeciallecture.payments.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
}
