package com.example.springspeciallecture.subscribe.repository;

import com.example.springspeciallecture.subscribe.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
